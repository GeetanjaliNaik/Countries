package com.project.countries.ui.screens.details

import android.os.Environment
import com.project.countries.CountriesApplication
import com.project.countries.R
import com.project.countries.database.DBHandler
import io.realm.Realm
import javax.inject.Inject
import android.util.Log
import android.widget.ImageView
import com.project.countries.api.datamanager.CountriesDataManager
import com.project.countries.data.remote.module.countries.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import com.project.countries.database.countries.*
import io.realm.RealmList
import java.io.*
import java.lang.Exception


/**
 * Created by Geeta on 04/02/19.
 */
class CountryDetailActPresenter @Inject constructor():  CountryDetailActContract.Presenter{


    @Inject
    lateinit var countriesDataManager: CountriesDataManager
    var view: CountryDetailActContract.View?=null
    override fun saveCountryData(request: Country) {
        view!!.showHideProgressDialog(true)

        DBHandler.getInstance().getRealm()!!.executeTransaction(Realm.Transaction { realm ->
            try {

                saveCountry(realm,convertDataToRealm(request!!))
                downloadFlag(request.flag!!, request.numericCode!!)
            }
            catch (exp:Exception)
            {
                view!!.updateSaveUI(exp.message!!)
            }
        })



    }

    fun saveCountry(realm: Realm,country: CountryDbData) {

       realm.copyToRealmOrUpdate(country)

    }

    fun updateFlag(flag: String,numericCode:String)
    {
        DBHandler.getInstance().getRealm()!!.executeTransaction{
            try {
                var model: CountryDbData? = it.where(CountryDbData::class.java).equalTo(
                    "numericCode",
                    numericCode
                ).findFirst()
                if (model == null) {
                    view!!.updateSaveUI("No Data Available")
                } else {
                    model.flag = flag
                    it.copyToRealmOrUpdate(model)
                }
            }  catch (exp:Exception)
                {
                    view!!.updateSaveUI(exp.message!!)
                }
        }


    }
    fun downloadFlag(imageName:String,numericCode: String)
    {
        var name=imageName.substring(imageName.lastIndexOf("/")+1)
        countriesDataManager.downloadFlagImage(name).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe({ result ->
            var filepath=getFilePath()+"/"+name
            val isSDPresent = android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED
            if(isSDPresent) {
                if (writeResponseBodyToDisk(result, name)) {
                    updateFlag(filepath, numericCode)
                    view!!.showHideProgressDialog(false)
                    view!!.updateSaveUI(CountriesApplication.getApplicationContext().resources.getString(R.string.data_save_sussessfully))
                } else {
                    view!!.showHideProgressDialog(false)
                    view!!.showAlert("Unable to Save image")
                }
            }
            else
            {
                if(writeResponseBodyToInternal(result,name)) {
                    updateFlag(filepath,numericCode)
                    view!!.showHideProgressDialog(false)
                    view!!.updateSaveUI(CountriesApplication.getApplicationContext().resources.getString(R.string.data_save_sussessfully))
                }
                else{
                    view!!.showHideProgressDialog(false)
                    view!!.showAlert("Unable to Save image")
                }
            }
        }, { error ->
            view?.let {
                view!!.showHideProgressDialog(false)
                view!!.showAlert(error.localizedMessage)
//                CommonResponseParser.ErrorParser.parseError(error, true)?.let { it1 -> view!!.showToastAlert(it1) }
            }
        })
    }


    override fun attachView(view: CountryDetailActContract.View) {
        this.view=view
    }

    override fun detachView() {
        this.view=null
    }
    private fun writeResponseBodyToDisk(body: ResponseBody, documentName: String): Boolean {
        try {
            val futureStudioIconFile = File(getFilePath() + "/" + documentName)

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(10000)

                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)

                    if (read == -1) {
                        break
                    }

                    outputStream.write(fileReader, 0, read)

                    fileSizeDownloaded += read.toLong()

                    Log.d("Limit History", "file download: $fileSizeDownloaded of $fileSize")
                }

                outputStream.flush()

                return true
            } catch (e: IOException) {
                return false
            } finally {
                if (inputStream != null) {
                    inputStream.close()
                }

                if (outputStream != null) {
                    outputStream.close()
                }
            }
        } catch (e: IOException) {
            return false
        }

    }

    fun getFilePath(): String {
        val file = File(Environment.getExternalStorageDirectory().path, "Countries" )
        if (!file.exists()) {
            file.mkdirs()
        }
        return file.absolutePath

    }
    fun writeResponseBodyToInternal(body: ResponseBody, documentName: String):Boolean{

        try {
            val futureStudioIconFile = File(getinterFilePath() + "/" + documentName)

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(10000)

                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)

                    if (read == -1) {
                        break
                    }

                    outputStream.write(fileReader, 0, read)

                    fileSizeDownloaded += read.toLong()

                    Log.d("Limit History", "file download: $fileSizeDownloaded of $fileSize")
                }

                outputStream.flush()

                return true
            } catch (e: IOException) {
                return false
            } finally {
                if (inputStream != null) {
                    inputStream.close()
                }

                if (outputStream != null) {
                    outputStream.close()
                }
            }
        } catch (e: IOException) {
            return false
        }

    }
    fun getinterFilePath(): String {
        val file = File(CountriesApplication.getApplicationContext().filesDir, "Countries" )
        if (!file.exists()) {
            file.mkdirs()
        }
        return file.absolutePath

    }
    private fun convertDataToRealm(request: Country): CountryDbData {
        var country=CountryDbData()
        country.numericCode=request.numericCode
        country!!.name=request.name
        country!!.capital=request.capital
        country!!.flag = request.flag
        country!!.languages=convertRealmLanguage(request.languages!!)
        if(request.callingCodes!=null && request.callingCodes!!.size!=0)
            country!!.callingCodes= convertToRealmList(request.callingCodes!!) as RealmList<String>?
        country!!.subregion=request.subregion
        country!!.region=request.region

        if(request.timezones!=null && request.timezones!!.size!=0)
            country!!.timezones= convertToRealmList(request.timezones!!) as RealmList<String>?

        country!!.currencies=convertRealmCurrencies(request.currencies!!)
        /*country!!.area=request.area
        country!!.nativeName=request.nativeName

        country!!.demonym= request.demonym

        country!!.alpha2Code=request.alpha2Code

        if(request.borders!=null && request.borders!!.size!=0)
            country!!.borders= convertToRealmList(request.borders!!) as RealmList<String>?




        country!!.regionalBlocs=convertRealmregionalBlocs(request.regionalBlocs!!)
        country!!.gini=request.gini
        country!!.population=request.population

        country!!.alpha3Code=request.alpha3Code
        if(request.topLevelDomain!=null && request.topLevelDomain!!.size!=0)
            country!!.topLevelDomain= convertToRealmList(request.topLevelDomain!!) as RealmList<String>?

        country!!.cioc=request.cioc
        country!!.translations=convertRealmTranslation(request.translations)

        if(request.altSpellings!=null && request.altSpellings!!.size!=0)
            country!!.altSpellings= convertToRealmList(request.altSpellings!!) as RealmList<String>?

        if(request.latlng!=null && request.latlng!!.size!=0)
            country!!.latlng= convertToRealmList(request.latlng!!) as RealmList<Double>?*/

        return country!!;

    }

    private fun convertRealmCurrencies(currencies: List<Country.CurrenciesItem>): RealmList<CurrenciesItem>? {
        var currenciesListRealm:RealmList<CurrenciesItem>?= RealmList()
        var currenciesItem:CurrenciesItem?=null
        for(currency in currencies)
        {
            currenciesItem=null
            currenciesItem= CurrenciesItem(currency.symbol,currency.code,currency.name)
            currenciesListRealm!!.add(currenciesItem)

        }
        return currenciesListRealm
    }

   /* private fun convertRealmTranslation(translations: Country.Translations?): Translations? {
        var translationRealm:Translations?=Translations(translations!!.br,translations!!.de,
            translations!!.pt, translations!!.ja,
            translations!!.hr, translations!!.it,
            translations!!.fa, translations!!.fr,
            translations!!.es, translations!!.nl)
        return translationRealm
    }*/

   /* private fun convertRealmregionalBlocs(regionalBlocs: List<Country.RegionalBlocsItem>): RealmList<RegionalBlocsItem>? {
        var regionalBlocsList:RealmList<RegionalBlocsItem>?=RealmList()
        var regionalBlocsItem:RegionalBlocsItem?=null
        for(regionalBloc in regionalBlocs)
        {
            regionalBlocsItem=null
            regionalBlocsItem=RegionalBlocsItem()
            regionalBlocsItem.acronym=regionalBloc.acronym
            regionalBlocsItem.name= regionalBloc.name
            regionalBlocsItem.otherNames=convertToRealmList(regionalBloc.otherNames!!) as RealmList<String>?
            regionalBlocsItem.otherAcronyms=convertToRealmList(regionalBloc.otherAcronyms!!) as RealmList<String>?

            regionalBlocsList!!.add(regionalBlocsItem)

        }
        return regionalBlocsList
    }*/

    private fun convertRealmLanguage(languages: List<Country.LanguagesItem>): RealmList<LanguagesItem>? {
        var languagesRealm: RealmList<LanguagesItem>?= RealmList()
        var languagesItem:LanguagesItem?=null
        for(languag in languages)
        {
            languagesItem=null
            languagesItem=LanguagesItem()
            languagesItem.nativeName =languag.nativeName
            languagesItem.iso639_2 =languag.iso639_2
            languagesItem.name =languag.name
            languagesItem.iso639_1 =languag.iso639_1
            languagesRealm!!.add(languagesItem)

        }
        return languagesRealm
    }
    fun convertToRealmList(list:List<Any>):RealmList<Any>
    {
        var realmList:RealmList<Any> = RealmList<Any>()
        for(item in list)
            realmList.add(item)
        return realmList
    }
}