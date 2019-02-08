package com.project.countries.utiles

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Environment
import android.os.StatFs
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.net.Inet4Address
import java.net.NetworkInterface

import java.net.SocketException

/**
 * Created by Geeta on 04/02/19.
 */
class AppUtils {
    companion object {



        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun convertDateFormat(dateString: String, currentDatePattern: String, requiredDatePattern: String): String {
            if (dateString.isEmpty()) {
                return ""
            }
            var reqDateString = ""
            val dateFormat = SimpleDateFormat(currentDatePattern, Locale.ENGLISH)
            val reqFormat = SimpleDateFormat(requiredDatePattern, Locale.ENGLISH)
            try {
                val date = dateFormat.parse(dateString)
                reqDateString = reqFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return reqDateString
        }
        fun availableEXTMemorySpace(): Long {
            val stat_fs = StatFs(Environment.getExternalStorageDirectory().getPath())
            val bytesAvailable: Long
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                bytesAvailable = stat_fs.getBlockSizeLong() * stat_fs.getAvailableBlocksLong()
            } else {
                bytesAvailable = stat_fs.getBlockSize().toLong() * stat_fs.getAvailableBlocks().toLong()
            }
            val megAvailable = bytesAvailable / (1024 * 1024)

            return megAvailable
        }


    }


}