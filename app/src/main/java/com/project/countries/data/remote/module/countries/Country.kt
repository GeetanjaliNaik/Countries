package com.project.countries.data.remote.module.countries

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
/**
 * Created by Geeta on 04/02/19.
 */
data class Country(/*@SerializedName("area")
                   var area: Double? = 0.0,*/
                  /* @SerializedName("nativeName")
                   var nativeName: String? = "",*/
                   @SerializedName("capital")
                   var capital: String? = "",
                  /* @SerializedName("demonym")
                   var demonym: String? = "",*/
                   @SerializedName("flag")
                   var flag: String? = "",
                   /*@SerializedName("alpha2Code")
                   var alpha2Code: String? = "",*/
                   @SerializedName("languages")
                   var languages: List<LanguagesItem>??=null,
                   /*@SerializedName("borders")
                   var borders: List<String>??=null,*/
                   @SerializedName("subregion")
                   var subregion: String? = "",
                   @SerializedName("callingCodes")
                   var callingCodes: List<String>??=null,
                   /*@SerializedName("regionalBlocs")
                   var regionalBlocs: List<RegionalBlocsItem>??=null,
                   @SerializedName("gini")
                   var gini: Double? = 0.0,
                   @SerializedName("population")
                   var population: Long? = 0,*/
                   @SerializedName("numericCode")
                   var numericCode: String? = "",
                  /* @SerializedName("alpha3Code")
                   var alpha3Code: String? = "",*/
                  /* @SerializedName("topLevelDomain")
                   var topLevelDomain: List<String>??=null,*/
                   @SerializedName("timezones")
                   var timezones: List<String>??=null,
                  /* @SerializedName("cioc")
                   var cioc: String? = "",
                   @SerializedName("translations")
                   var translations: Translations?=null,*/
                   @SerializedName("name")
                   var name: String? = "",
                   /*@SerializedName("altSpellings")
                   var altSpellings: List<String>??=null,*/
                   @SerializedName("region")
                   var region: String? = "",
                  /* @SerializedName("latlng")
                   var latlng: List<Double>??=null,*/
                   @SerializedName("currencies")
                   var currencies: List<CurrenciesItem>??=null)
{
    data class CurrenciesItem(@SerializedName("symbol")
                              var symbol: String? = "",
                              @SerializedName("code")
                              var code: String? = "",
                              @SerializedName("name")
                              var name: String? = "")
    data class LanguagesItem(@SerializedName("nativeName")
                             var nativeName: String? = "",
                             @SerializedName("iso639_2")
                             var iso639_2: String? = "",
                             @SerializedName("name")
                             var name: String? = "",
                             @SerializedName("iso639_1")
                             var iso639_1: String? = "")
   /* data class RegionalBlocsItem(@SerializedName("acronym")
                                 var acronym: String? = "",
                                 @SerializedName("name")
                                 var name: String? = "",
                                 @SerializedName("otherNames")
                                 var otherNames: List<String>?=null,
                                 @SerializedName("otherAcronyms")
                                 var otherAcronyms: List<String>?=null
    )*/
   /* data class Translations(@SerializedName("br")
                            var br: String? = "",
                            @SerializedName("de")
                            var de: String? = "",
                            @SerializedName("pt")
                            var pt: String? = "",
                            @SerializedName("ja")
                            var ja: String? = "",
                            @SerializedName("hr")
                            var hr: String? = "",
                            @SerializedName("it")
                            var it: String? = "",
                            @SerializedName("fa")
                            var fa: String? = "",
                            @SerializedName("fr")
                            var fr: String? = "",
                            @SerializedName("es")
                            var es: String? = "",
                            @SerializedName("nl")
                            var nl: String? = "")*/
}