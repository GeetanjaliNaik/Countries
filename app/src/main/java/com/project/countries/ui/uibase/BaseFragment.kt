package com.project.countries.ui.uibase


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import android.widget.LinearLayout
import com.project.countries.R
import com.project.countries.utiles.ProgressDialogHelper


/**
 * Created by Geeta on 04/02/19.
 */
open class BaseFragment : Fragment(), HasSupportFragmentInjector {
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector

    lateinit var mContext: Context

    private var progressDialogHelper: ProgressDialogHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity!!
        progressDialogHelper = ProgressDialogHelper(mContext)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachToContext(context)
    }


    /*
     * Called when the fragment attaches to the context
     */
    protected fun onAttachToContext(context: Context) {
        AndroidSupportInjection.inject(this)
    }

    fun showToastAlert(message: String) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this@BaseFragment.mContext, message, Toast.LENGTH_SHORT).show()
        }
    }
    fun showLongToastAlert(message: String) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this@BaseFragment.mContext, message, Toast.LENGTH_LONG).show()
        }
    }

    fun showAlert(message: String) {
        val builder = android.support.v7.app.AlertDialog.Builder(mContext, R.style.MaterialAlertDialogStyle)
        builder.setMessage(message).setTitle(R.string.app_name).setCancelable(true)

        builder.setNeutralButton("OK", { dialog, which ->
            dialog.dismiss()
        })
        builder.create().show()
    }

    fun hideSoftKeyboard(v: View?) {
        if (v != null) {
            val inputMethodManager = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    fun showHideProgressDialog(isShow: Boolean) {
        if (isShow) {
            progressDialogHelper?.show()
        } else {
            progressDialogHelper?.dismiss()
        }
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 2
    }

}