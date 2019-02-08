package com.project.countries.database.countries

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.RealmClass
/**
 * Created by Geeta on 04/02/19.
 */
@RealmClass
open class LanguagesItem(@SerializedName("nativeName")
                         var nativeName: String? = "",
                         @SerializedName("iso639_2")
                         var iso639_2: String? = "",
                         @SerializedName("name")
                         var name: String? = "",
                         @SerializedName("iso639_1")
                         var iso639_1: String? = ""): RealmObject()