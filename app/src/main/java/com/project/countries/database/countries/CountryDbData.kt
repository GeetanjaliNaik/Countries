package com.project.countries.database.countries

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
/**
 * Created by Geeta on 04/02/19.
 */

open class CountryDbData(/*@SerializedName("area")
                         var area: Double? = 0.0,
                         @SerializedName("nativeName")
                         var nativeName: String? = "",*/
                         @SerializedName("capital")
                         var capital: String? = "",
                        /* @SerializedName("demonym")
                         var demonym: String? = "",*/
                         @SerializedName("flag")
                         var flag: String? = "",
                        /* @SerializedName("alpha2Code")
                         var alpha2Code: String? = "",*/
                         @SerializedName("languages")
                         var languages: RealmList<LanguagesItem>?=null,
                         /*@SerializedName("borders")
                         var borders: RealmList<String>??=null,*/
                         @SerializedName("subregion")
                         var subregion: String? = "",
                         @SerializedName("callingCodes")
                         var callingCodes: RealmList<String>?=null,
                        /* @SerializedName("regionalBlocs")
                         var regionalBlocs: RealmList<RegionalBlocsItem>?=null,
                         @SerializedName("gini")
                         var gini: Double? = 0.0,
                         @SerializedName("population")
                         var population: Long? = 0,*/
                         @PrimaryKey @SerializedName("numericCode")
                         var numericCode: String? = "",
                         /*@SerializedName("alpha3Code")
                         var alpha3Code: String? = "",
                         @SerializedName("topLevelDomain")
                         var topLevelDomain: RealmList<String>?=null,*/
                         @SerializedName("timezones")
                         var timezones: RealmList<String>?=null,
                        /* @SerializedName("cioc")
                         var cioc: String? = "",
                         @SerializedName("translations")
                         var translations: Translations?=null,*/
                         @SerializedName("name")
                         var name: String? = "",
                        /* @SerializedName("altSpellings")
                         var altSpellings: RealmList<String>?=null,*/
                         @SerializedName("region")
                         var region: String? = "",
                         /*@SerializedName("latlng")
                         var latlng: RealmList<Double>??=null,*/
                         @SerializedName("currencies")
                         var currencies: RealmList<CurrenciesItem>?=null): RealmObject()