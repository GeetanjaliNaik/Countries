package com.project.countries.ui.screens.deshboard.offline

import com.project.countries.data.remote.module.countries.Country
import com.project.countries.database.countries.CountryDbData
import com.project.countries.ui.uibase.BasePresenter
import com.project.countries.ui.uibase.BaseView
/**
 * Created by Geeta on 04/02/19.
 */
interface CountriesOfflineFragContract {
    interface View : BaseView {
        fun updateUIforCountries(countryList: List<CountryDbData>?)
    }

    interface Presenter : BasePresenter<View> {

        fun getOfflineCountries()
    }
}