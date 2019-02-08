package com.project.countries.utiles;

import android.app.ProgressDialog
import android.content.Context
import com.project.countries.R
/**
 * Created by Geeta on 04/02/19.
 */
class ProgressDialogHelper(context: Context) {

    private var progressDialog: ProgressDialog? = null
    private var message: String? = null

    init {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            message = context.getString(R.string.message_loading)
        }
    }

    @Synchronized
    fun show() {
        progressDialog?.apply {
            setMessage(message)
            setCancelable(false)
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            show()
        }
    }

    @Synchronized
    fun dismiss() {
        if (progressDialog == null) {
            return
        }
        progressDialog?.dismiss()
    }
}
