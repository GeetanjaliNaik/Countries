package com.project.countries.ui.adaptor

import android.databinding.DataBindingUtil
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.countries.R
import com.project.countries.data.remote.module.countries.Country
import com.project.countries.database.countries.CountryDbData
import com.project.countries.databinding.AdapterCountriesOfflineListItemBinding
import com.project.countries.ui.screens.deshboard.offline.CountriesOfflineFragment
import com.project.countries.utiles.svg.svgurl.SvgLoader
//import com.project.countries.utiles.svg.GlideApp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_countries_offline_list_item.view.*
import java.io.File
import java.util.ArrayList
/**
 * Created by Geeta on 04/02/19.
 */
class CountriesOfflineListAdapter (private var cardData: ArrayList<CountryDbData>?, var deshboardFragment: Fragment) : RecyclerView.Adapter<CountriesOfflineListAdapter.CountriesListHolder>() {
    private val countriesListForFilter: ArrayList<CountryDbData>? = cardData
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListHolder {
//        val inflatedView = parent.inflate(R.layout.adapter_countries_online_offline_list_item, false)

        val inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_countries_offline_list_item, parent, false);

        return CountriesListHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return cardData!!.size
    }

    override fun onBindViewHolder(holder: CountriesListHolder, position: Int) {
        val itemPhoto = cardData?.get(position)
        holder.bindCardData(itemPhoto!!, position)
    }
    fun setAdapter()
    {
        cardData=countriesListForFilter
        notifyDataSetChanged()
    }
    private var recycleListSize: Int = 0

    fun setList(newlist: ArrayList<CountryDbData>) {
        cardData = ArrayList<CountryDbData>()
        cardData!!.addAll(newlist)
        recycleListSize = cardData!!.size
        notifyDataSetChanged()
    }

    fun setFilter(searchWord: String): ArrayList<CountryDbData>? {
        var searchWord = searchWord
        searchWord = searchWord.toLowerCase()

        val pageItemList = ArrayList<CountryDbData>()

        if (countriesListForFilter != null) {
            for (pageItem in countriesListForFilter) {
                var name = ""
                if (!TextUtils.isEmpty(pageItem.name)) {
                    if (pageItem.name!!.toLowerCase().contains(searchWord) ) {
                        pageItemList.add(pageItem)
                    }
                }
            }
        }

        return pageItemList
    }

    inner class CountriesListHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {


        //2
        private var view: View = v

        private var data: CountryDbData? = null
        private var binding: AdapterCountriesOfflineListItemBinding = DataBindingUtil.bind(view)!!
        fun bindCardData(boardData: CountryDbData, pos: Int) {
            this.data = boardData
            binding.country = boardData
            SvgLoader.pluck()
                .with(deshboardFragment.activity)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .loadfile(File(boardData.flag), view.iv_country_offline_flag)
            view.tag = pos
        }

        init {
            v.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            var pos = v!!.tag;
//            if(deshboardFragment is CountriesOfflineFragment)
            (deshboardFragment as CountriesOfflineFragment).viewCountryDetail(cardData!![pos as Int]!!)
        }
    }
}