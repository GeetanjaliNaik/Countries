package com.davinta.avanti.api

/**
 * Created by Geeta on 04/02/19.
 */
class RestConstant {
    companion object {

        const val BASE_API_URL = "https://restcountries.eu/"
        const val GET_COUNTRIES_LIST="rest/v2/name/{name}"
        const val DOWNLOAD_COUNTRY_FLAG="data/{imageName}"
    }
}