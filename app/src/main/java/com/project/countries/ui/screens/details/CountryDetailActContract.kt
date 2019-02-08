package com.project.countries.ui.screens.details

import android.graphics.Bitmap
import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import com.project.countries.data.remote.module.countries.Country
import com.project.countries.ui.uibase.BasePresenter
import com.project.countries.ui.uibase.BaseView
/**
 * Created by Geeta on 04/02/19.
 */
interface CountryDetailActContract {
    interface View : BaseView {
        fun updateSaveUI(message:String)

    }

    interface Presenter : BasePresenter<View> {

        fun saveCountryData(country: Country)
    }
}