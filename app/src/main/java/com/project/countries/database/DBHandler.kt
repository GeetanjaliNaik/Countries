package com.project.countries.database

import android.content.Context
import com.project.countries.CountriesApplication
import com.project.countries.listener.OnDBFetchListenerWithList
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import java.util.ArrayList
/**
 * Created by Geeta on 04/02/19.
 */
class DBHandler(){
    private var mRealm: Realm? = null

    companion object {
        var sDatabaseConnector: DBHandler? = null
        fun getInstance(): DBHandler {
            if (sDatabaseConnector == null) {
                sDatabaseConnector = DBHandler()
            }
            return sDatabaseConnector as DBHandler
        }
    }

    fun initRealm(context: Context?) {
        if (context != null) {
            Realm.init(context)
            // Get a Realm instance for this thread
            mRealm = Realm.getDefaultInstance()
        }
    }

    fun getRealm(): Realm? {
        if (mRealm != null)
            return mRealm
        else {
            initRealm(CountriesApplication.getApplicationContext())
            return mRealm
        }
    }
    fun getAll(className: Class<out RealmObject>, onDBFetchListener: OnDBFetchListenerWithList?) {
        DBHandler.getInstance().getRealm()!!.executeTransaction(Realm.Transaction { realm ->
            var modelList: List<RealmModel> = ArrayList()
            modelList = realm.where(className).findAll()

            if (onDBFetchListener != null) {
                onDBFetchListener!!.onFetchList(modelList)
            }
        })
    }
}