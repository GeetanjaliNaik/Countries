package com.project.countries.ui.screens.deshboard.online

import com.project.countries.data.remote.module.countries.Country
import com.project.countries.ui.uibase.BasePresenter
import com.project.countries.ui.uibase.BaseView
/**
 * Created by Geeta on 04/02/19.
 */

interface CountriesOnlineFragContract {
    interface View : BaseView {
        fun updateUIforCountries(countriesListResponse: List<Country>)
    }

    interface Presenter : BasePresenter<View> {

        fun getCountries(name:String)
    }
}