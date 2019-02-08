package com.project.countries.ui.screens.deshboard.online

import android.text.TextUtils
import android.util.Log
import com.project.countries.api.datamanager.CountriesDataManager
import com.project.countries.data.remote.module.countries.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
/**
 * Created by Geeta on 04/02/19.
 */
class CountriesOnlineFragPresenter  @Inject constructor():  CountriesOnlineFragContract.Presenter{
    @Inject
    lateinit var countriesDataManager: CountriesDataManager
    override fun getCountries(name: String) {
        view!!.showHideProgressDialog(true)
        countriesDataManager.getCountriesList(name).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ result ->

            view!!.showHideProgressDialog(false)


//                Log.i("kljl",result!!.size.toString())
            view!!.updateUIforCountries(result)
        }, { error ->
            view?.let {
                view!!.showHideProgressDialog(false)
//                CommonResponseParser.ErrorParser.parseError(error, true)?.let { it1 -> view!!.showToastAlert(it1) }
            }
        })
    }


    var view: CountriesOnlineFragContract.View? = null
    override fun attachView(view: CountriesOnlineFragContract.View) {
        this.view=view
    }

    override fun detachView() {
        this.view=null
    }
}