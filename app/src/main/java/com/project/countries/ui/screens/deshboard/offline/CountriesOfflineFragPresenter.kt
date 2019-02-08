package com.project.countries.ui.screens.deshboard.offline

import android.util.Log
import com.project.countries.data.remote.module.countries.Country
import com.project.countries.database.DBHandler
import com.project.countries.database.countries.CountryDbData
import com.project.countries.listener.OnDBFetchListenerWithList
import io.realm.RealmModel
import java.util.ArrayList
import javax.inject.Inject
/**
 * Created by Geeta on 04/02/19.
 */
class CountriesOfflineFragPresenter  @Inject constructor():  CountriesOfflineFragContract.Presenter{


    var view: CountriesOfflineFragContract.View? = null
    override fun attachView(view: CountriesOfflineFragContract.View) {
        this.view=view
    }

    override fun detachView() {
        this.view=null
    }
    override fun getOfflineCountries() {
        view!!.showHideProgressDialog(true)
        var countryList: ArrayList<CountryDbData>?= ArrayList()
        val dataHandler = DBHandler.getInstance()
        dataHandler.getAll(CountryDbData::class.java, object : OnDBFetchListenerWithList {
            override fun onFetchList(realmModel: List<RealmModel>) {
                if(realmModel!=null && realmModel.size!=0)
                for(realmObject in realmModel){
                    val country:CountryDbData = realmObject as CountryDbData

                    countryList!!.add(country)
                }
                view!!.showHideProgressDialog(false)
                view!!.updateUIforCountries(countryList)
            }
        })
    }
}