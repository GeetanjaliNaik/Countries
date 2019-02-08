package com.project.countries.api
import com.project.countries.api.services.CountriesService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Geeta on 04/02/19.
 */
@Singleton
class BaseApiManager @Inject constructor(private var retrofit: Retrofit) {
    lateinit var countriesService: CountriesService


    private fun initApiManager() {
        countriesService = create(CountriesService::class.java)
    }

    fun <T> create(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    init {
        initApiManager()
    }

}