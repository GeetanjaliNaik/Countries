package com.project.countries.listener

import io.realm.RealmModel
/**
 * Created by Geeta on 04/02/19.
 */
interface OnDBFetchListenerWithList {
    abstract fun onFetchList(realmModel: List<RealmModel>)
}