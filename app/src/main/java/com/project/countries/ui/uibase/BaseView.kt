package com.project.countries.ui.uibase
/**
 * Created by Geeta on 04/02/19.
 */
interface BaseView {
    fun showHideProgressDialog(isShow: Boolean)

    fun showAlert(message: String)

    fun showToastAlert(message: String)


}