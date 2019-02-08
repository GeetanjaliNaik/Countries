package com.project.countries.utiles

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by Geeta on 04/02/19.
 */
class PreferenceUtil {

    companion object {
        fun newInstance() = PreferenceUtil()
    }

    fun set(context: Context, key: String, value: Any) {
        val editor = getSharedPreference(context).edit();
        when (value) {
            is String -> {
                editor.putString(key, value).commit()
            }

            is Int -> editor.putInt(key, value).commit()

            is Boolean -> editor.putBoolean(key, value).commit()
            is Float -> editor.putFloat(key, value).commit()
            is Long -> editor.putLong(key, value).commit()
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun getString(context: Context, key: String): String {
        return getSharedPreference(context).getString(key, "")
    }

    inline fun <reified T : Any> get(context: Context, key: String, defaultValue: T? = null): T? {
        val pref = getSharedPreference(context);
        return when (T::class) {
            String::class -> pref.getString(key, defaultValue as? String) as T?
            Int::class -> pref.getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> pref.getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> pref.getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> pref.getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
    fun getSharedPreference(context: Context) : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}