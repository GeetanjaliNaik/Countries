package com.project.countries.database.countries

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.RealmClass
/**
 * Created by Geeta on 04/02/19.
 */
@RealmClass
open class CurrenciesItem(@SerializedName("symbol")
                          var symbol: String? = "",
                          @SerializedName("code")
                          var code: String? = "",
                          @SerializedName("name")
                          var name: String? = ""): RealmObject()