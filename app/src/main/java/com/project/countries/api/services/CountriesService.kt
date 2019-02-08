package com.project.countries.api.services

import com.davinta.avanti.api.RestConstant
import com.project.countries.data.remote.module.countries.Country
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Created by Geeta on 04/02/19.
 */
interface CountriesService {
    @Headers("Fineract-Platform-TenantId: default", "Content-Type: application/json")
    @GET(RestConstant.GET_COUNTRIES_LIST)
    abstract fun getCountriesList(@Path("name") name: String): Observable<List<Country>/*String*/>

    @Streaming
    @GET(RestConstant.DOWNLOAD_COUNTRY_FLAG)
    abstract fun downloadFlagImage(@Path("imageName") imageName: String): Observable<ResponseBody>
}