package com.project.countries.ui.uibase

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.project.countries.R
import com.project.countries.utiles.AppUtils
import com.project.countries.utiles.PreferenceUtil
import com.project.countries.utiles.ProgressDialogHelper
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by Geeta on 04/02/19.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {
    //    protected var progressDialog: Dialog? = null
    private var mDisposables: CompositeDisposable? = null

    abstract fun busInputReceived(busModal: Any?)

//    @Inject
//    lateinit var rxBus: RxBus

    var progressDialogHelper: ProgressDialogHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInternetStatus()
        progressDialogHelper = ProgressDialogHelper(this)
    }

    private fun checkInternetStatus() {
        val connectionStatus = AppUtils.isNetworkAvailable(applicationContext)
        PreferenceUtil.newInstance().set(this, "INTERNET", connectionStatus)
    }

    override fun onResume() {
        super.onResume()
    }




    override fun onDestroy() {
        super.onDestroy()
        mDisposables?.clear()

    }

    override fun onPause() {
        super.onPause()
    }

    fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this@BaseActivity, R.style.MaterialAlertDialogStyle)

        builder.setMessage(message).setTitle(R.string.app_name).setCancelable(true)
        builder.setCancelable(true).setIcon(R.mipmap.ic_launcher)
        builder.create().show()
    }

    fun showAlertWithOkButton(message: String) {
        val builder = AlertDialog.Builder(this@BaseActivity, R.style.MaterialAlertDialogStyle)
        builder.setMessage(message).setTitle(R.string.app_name).setCancelable(true)
        builder.setNeutralButton("Ok") { dialog, which -> dialog.dismiss() }
        builder.create().show()
    }

    fun hideSoftKeyboard(v: View?) {
        if (v != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun moveNextTo(nextActivity: Class<*>) {
        val intent = Intent(this, nextActivity)
        startActivity(intent)
    }

    fun moveNextToFinishingIt(nextActivity: Class<*>) {
        moveNextTo(nextActivity)
        finish()
    }

    fun showErrorMessage(message: String) {
        showAlert(message)
    }

    fun showHideProgressDialog(isShow: Boolean) {
        if (isShow) {
            progressDialogHelper?.show()
        } else {
            progressDialogHelper?.dismiss()
        }
    }

    fun showToastAlert(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
