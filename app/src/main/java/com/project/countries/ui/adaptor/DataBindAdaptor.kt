package com.project.countries.ui.adaptor

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import android.graphics.Color
import android.text.TextUtils
import android.widget.LinearLayout
import com.project.countries.CountriesApplication
import com.project.countries.R
import com.project.countries.data.remote.module.countries.Country
import com.project.countries.database.countries.CountryDbData
import com.project.countries.database.countries.CurrenciesItem
import com.project.countries.database.countries.LanguagesItem

/**
 * Created by Geeta on 04/02/19.
 */
class DataBindAdaptor {
    companion object {
        @JvmStatic
        @BindingAdapter("viewVisibility")
        fun setViewVisibility(view: View, data: String?) {
            if(!data.isNullOrEmpty()) {
                view.visibility=View.VISIBLE
            }
            else
                view.visibility=View.GONE
        }


       /* @JvmStatic
        @BindingAdapter("android:alphaCode")
        fun setAlphaCode(view: TextView?, country: Country) {
            var alphacode:String?=null
            if(!TextUtils.isEmpty(country.alpha2Code)&& !TextUtils.isEmpty(country.alpha3Code))
                alphacode=country.alpha2Code+" , "+country.alpha3Code
            else if(!TextUtils.isEmpty(country.alpha2Code)&&TextUtils.isEmpty(country.alpha3Code))
                alphacode=country.alpha2Code
            else if(TextUtils.isEmpty(country.alpha2Code)&&!TextUtils.isEmpty(country.alpha3Code))
                alphacode=country.alpha3Code
            if(alphacode!=null)
                view!!.text=alphacode
        }
        @JvmStatic
        @BindingAdapter("android:alphaCodeDb")
        fun setAlphaCode(view: TextView?, country: CountryDbData) {
            var alphacode:String?=null
            if(!TextUtils.isEmpty(country.alpha2Code)&& !TextUtils.isEmpty(country.alpha3Code))
                alphacode=country.alpha2Code+" , "+country.alpha3Code
            else if(!TextUtils.isEmpty(country.alpha2Code)&&TextUtils.isEmpty(country.alpha3Code))
                alphacode=country.alpha2Code
            else if(TextUtils.isEmpty(country.alpha2Code)&&!TextUtils.isEmpty(country.alpha3Code))
                alphacode=country.alpha3Code
            if(alphacode!=null)
                view!!.text=alphacode
        }*/
        @JvmStatic
        @BindingAdapter("android:altspellings")
        fun setaltspellings(view: TextView?, altSpell:List<String>??) {
            if(altSpell!=null && altSpell.size!=0)
            {

                for(i in altSpell.indices)
                {
                    if(i+1==altSpell.size)
                        view!!.append(altSpell[i])
                    else{
                        view!!.append(altSpell[i])
                        view!!.append(" , ")
                    }

                }
            }

        }
        @JvmStatic
        @BindingAdapter("android:language")
        fun setLanguage(view: LinearLayout?, languagelist:List<Country.LanguagesItem>??) {
            if(languagelist!=null && languagelist.size!=0)
            {
                for(language in languagelist)
                {
                    if(!TextUtils.isEmpty(language.name))
                    {
                        view!!.addView(getDynamicTv("Name : "+language.name))
                    }
                    if(!TextUtils.isEmpty(language.nativeName))
                    {
                        view!!.addView(getDynamicTv("Native Name : "+language.nativeName))
                    }
                    if(!TextUtils.isEmpty(language.iso639_1))
                    {
                        view!!.addView(getDynamicTv("ISO 1 : "+language.iso639_1))
                    }
                    if(!TextUtils.isEmpty(language.iso639_2))
                    {
                       view!!.addView(getDynamicTv("ISO 2 : "+language.iso639_2))
                    }
                }
            }

        }
        @JvmStatic
        @BindingAdapter("android:currencies")
        fun setCurrenies(view: LinearLayout?, currenciesItemList:List<Country.CurrenciesItem>??) {
            if(currenciesItemList!=null && currenciesItemList.size!=0)
            {
                for(currency in currenciesItemList)
                {
                    if(!TextUtils.isEmpty(currency.name))
                    {
                        view!!.addView(getDynamicTv("Name : "+currency.name))
                    }
                    if(!TextUtils.isEmpty(currency.code))
                    {
                        view!!.addView(getDynamicTv("Code : : "+currency.code))
                    }
                    if(!TextUtils.isEmpty(currency.symbol))
                    {
                        view!!.addView(getDynamicTv("Symbol : "+currency.symbol))
                    }

                }
            }

        }
        @JvmStatic
        @BindingAdapter("android:currenciesDb")
        fun setCurreniesDb(view: LinearLayout?, currenciesItemList:List<CurrenciesItem>??) {
            if(currenciesItemList!=null && currenciesItemList.size!=0)
            {
                for(currency in currenciesItemList)
                {
                    if(!TextUtils.isEmpty(currency.name))
                    {
                        view!!.addView(getDynamicTv("Name : "+currency.name))
                    }
                    if(!TextUtils.isEmpty(currency.code))
                    {
                        view!!.addView(getDynamicTv("Code : : "+currency.code))
                    }
                    if(!TextUtils.isEmpty(currency.symbol))
                    {
                        view!!.addView(getDynamicTv("Symbol : "+currency.symbol))
                    }

                }
            }

        }
        @JvmStatic
        @BindingAdapter("android:languageDb")
        fun setLanguageDB(view: LinearLayout?, languagelist:List<LanguagesItem>??) {
            if(languagelist!=null && languagelist.size!=0)
            {
                for(language in languagelist)
                {
                    if(!TextUtils.isEmpty(language.name))
                    {
                        view!!.addView(getDynamicTv("Name : "+language.name))
                    }
                    if(!TextUtils.isEmpty(language.nativeName))
                    {
                        view!!.addView(getDynamicTv("Native Name : "+language.nativeName))
                    }
                    if(!TextUtils.isEmpty(language.iso639_1))
                    {
                        view!!.addView(getDynamicTv("ISO 1 : "+language.iso639_1))
                    }
                    if(!TextUtils.isEmpty(language.iso639_2))
                    {
                        view!!.addView(getDynamicTv("ISO 2 : "+language.iso639_2))
                    }
                }
            }

        }

       /* @JvmStatic
        @BindingAdapter("android:regionalblocs")
        fun setregionalblocs(view: LinearLayout?, reglist:List<Country.RegionalBlocsItem>??) {
            if(reglist!=null && reglist.size!=0)
            {

                for(region in reglist)
                {
                    var textView:TextView?=null
                    if(!TextUtils.isEmpty(region.name))
                    {
                        view!!.addView(getDynamicTv("Name : "+region.name))
                    }
                    if(!TextUtils.isEmpty(region.acronym))
                    {
                        view!!.addView(getDynamicTv("Acronym Name : "+region.acronym))
                    }
                   if(region.otherNames!=null && region.otherNames!!.size!=0)
                   {
                       textView=null
                       textView= getDynamicTv("Other Name : ")
                       setlistappend(textView,region.otherNames)
                       view!!.addView(textView)
                   }
                    if(region.otherAcronyms!=null && region.otherAcronyms!!.size!=0)
                    {
                        textView=null
                        textView= getDynamicTv("Other Acronyms : ")
                        setlistappend(textView,region.otherAcronyms)
                        view!!.addView(textView)
                    }
                    textView=null
                }
            }

        }*/
       /* @JvmStatic
        @BindingAdapter("android:regionalblocsdb")
        fun setregionalblocsdb(view: LinearLayout?, reglist:List<RegionalBlocsItem>??) {
            if(reglist!=null && reglist.size!=0)
            {

                for(region in reglist)
                {
                    var textView:TextView?=null
                    if(!TextUtils.isEmpty(region.name))
                    {
                        view!!.addView(getDynamicTv("Name : "+region.name))
                    }
                    if(!TextUtils.isEmpty(region.acronym))
                    {
                        view!!.addView(getDynamicTv("Acronym Name : "+region.acronym))
                    }
                    if(region.otherNames!=null && region.otherNames!!.size!=0)
                    {
                        textView=null
                        textView= getDynamicTv("Other Name : ")
                        setlistappend(textView,region.otherNames)
                        view!!.addView(textView)
                    }
                    if(region.otherAcronyms!=null && region.otherAcronyms!!.size!=0)
                    {
                        textView=null
                        textView= getDynamicTv("Other Acronyms : ")
                        setlistappend(textView,region.otherAcronyms)
                        view!!.addView(textView)
                    }
                    textView=null
                }
            }

        }
        @JvmStatic
        @BindingAdapter("android:translationNamedb")
        fun setTranslationDb(view: LinearLayout?, translations: Translations) {

            if(!TextUtils.isEmpty(translations.de))
            {
                view!!.addView(getDynamicTv("DE : "+translations.de))
            }
            if(!TextUtils.isEmpty(translations.es))
            {
                view!!.addView(getDynamicTv("ES : "+translations.es))
            }
            if(!TextUtils.isEmpty(translations.fr))
            {
                view!!.addView(getDynamicTv("FR : "+translations.fr))
            }
            if(!TextUtils.isEmpty(translations.ja))
            {
                view!!.addView(getDynamicTv("JA : "+translations.ja))
            }
            if(!TextUtils.isEmpty(translations.it))
            {
                view!!.addView(getDynamicTv("IT : "+translations.it))
            }
            if(!TextUtils.isEmpty(translations.br))
            {
                view!!.addView(getDynamicTv("BR : "+translations.br))
            }
            if(!TextUtils.isEmpty(translations.pt))
            {
                view!!.addView(getDynamicTv("PT : "+translations.pt))
            }
            if(!TextUtils.isEmpty(translations.nl))
            {
                view!!.addView(getDynamicTv("NL : "+translations.nl))
            }
            if(!TextUtils.isEmpty(translations.hr))
            {
                view!!.addView(getDynamicTv("HR : "+translations.hr))
            }
            if(!TextUtils.isEmpty(translations.fa))
            {
                view!!.addView(getDynamicTv("FA : "+translations.fa))
            }
        }
        @JvmStatic
        @BindingAdapter("android:translationName")
        fun setTranslation(view: LinearLayout?, translations: Country.Translations) {

            if(!TextUtils.isEmpty(translations.de))
            {
                view!!.addView(getDynamicTv("DE : "+translations.de))
            }
            if(!TextUtils.isEmpty(translations.es))
            {
                view!!.addView(getDynamicTv("ES : "+translations.es))
            }
            if(!TextUtils.isEmpty(translations.fr))
            {
                view!!.addView(getDynamicTv("FR : "+translations.fr))
            }
            if(!TextUtils.isEmpty(translations.ja))
            {
                view!!.addView(getDynamicTv("JA : "+translations.ja))
            }
            if(!TextUtils.isEmpty(translations.it))
            {
                view!!.addView(getDynamicTv("IT : "+translations.it))
            }
            if(!TextUtils.isEmpty(translations.br))
            {
                view!!.addView(getDynamicTv("BR : "+translations.br))
            }
            if(!TextUtils.isEmpty(translations.pt))
            {
                view!!.addView(getDynamicTv("PT : "+translations.pt))
            }
            if(!TextUtils.isEmpty(translations.nl))
            {
                view!!.addView(getDynamicTv("NL : "+translations.nl))
            }
            if(!TextUtils.isEmpty(translations.hr))
            {
                view!!.addView(getDynamicTv("HR : "+translations.hr))
            }
            if(!TextUtils.isEmpty(translations.fa))
            {
                view!!.addView(getDynamicTv("FA : "+translations.fa))
            }
        }*/
        @JvmStatic
        @BindingAdapter("android:textappend")
        fun setlistappend(view: TextView?, list:List<String>??) {
            if(list!=null && list.size!=0)
            {

                for(i in list.indices)
                {
                    if(i+1==list.size)
                        view!!.append(list[i])
                    else{
                        view!!.append(list[i])
                        view!!.append(" , ")
                    }

                }
            }

        }
        @JvmStatic
        @BindingAdapter("android:viewVisibility")
        fun setViewVisibility(view: View, data: List<Any>?) {
            if(data!=null && data!!.size!=0) {
                view.visibility=View.VISIBLE
            }
            else
                view.visibility=View.GONE
        }

        fun getDynamicTv(data:String):TextView
      {
          val view=View.inflate(
              CountriesApplication.getApplicationContext(),
              R.layout.dynamic_text_view ,null) as TextView
          view.text=data
          return view
      }
    }
}