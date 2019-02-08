package com.project.countries.utiles


import com.project.countries.data.remote.module.countries.Country
import com.project.countries.database.countries.CountryDbData
import javax.inject.Singleton

/**
 * Created by Geeta on 04/02/19.
 */
@Singleton
class AppData {
    var accessToken: String = ""
    var officeId: Long = 0
    var country:Country?=null
    var countryDb:CountryDbData?=null

}