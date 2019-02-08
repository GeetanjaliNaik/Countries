package com.project.countries.api.datamanager


import com.project.countries.api.BaseApiManager
import com.project.countries.data.remote.module.countries.Country
import com.project.countries.utiles.AppData
import io.reactivex.Observable
import okhttp3.ResponseBody
import javax.inject.Inject
/**
 * Created by Geeta on 04/02/19.
 */
class CountriesDataManager @Inject constructor(var mBaseApiManager: BaseApiManager) {

    @Inject
    lateinit var appData: AppData

    fun getCountriesList(name: String): Observable<List<Country>/*String*/> {
        return mBaseApiManager.countriesService.getCountriesList(name)
    }
    fun downloadFlagImage(imagename: String): Observable<ResponseBody>{
        return mBaseApiManager.countriesService.downloadFlagImage(imagename)
    }
}