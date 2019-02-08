package com.project.countries.ui.adaptor

import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.countries.R
import com.project.countries.data.remote.module.countries.Country
import com.project.countries.databinding.AdapterCountriesOnlineListItemBinding
import com.project.countries.ui.screens.deshboard.online.CountriesOnlineFragment
import java.util.ArrayList
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Looper
import android.widget.ImageView
import com.project.countries.utiles.svg.svgurl.SvgLoader
import kotlinx.android.synthetic.main.adapter_countries_online_list_item.view.*


/**
 * Created by Geeta on 04/02/19.
 */
class CountriesOnlineListAdapter (private var cardData: ArrayList<Country>?, var deshboardFragment: Fragment) : RecyclerView.Adapter<CountriesOnlineListAdapter.CountriesListHolder>() {
    private val countriesListForFilter: ArrayList<Country>? = cardData
    private val drawableMap: MutableMap<String, Drawable>? = HashMap<String, Drawable>()

//    private var requestBuilder: RequestBuilder<PictureDrawable>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListHolder {
//        val inflatedView = parent.inflate(R.layout.adapter_countries_online_offline_list_item, false)


        val inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_countries_online_list_item, parent, false);
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

    fun setList(newlist: ArrayList<Country>) {
        cardData = ArrayList<Country>()
        cardData!!.addAll(newlist)
        recycleListSize = cardData!!.size
        notifyDataSetChanged()
    }

    fun setFilter(searchWord: String): ArrayList<Country>? {
        var searchWord = searchWord
        searchWord = searchWord.toLowerCase()

        val pageItemList = ArrayList<Country>()

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

        private var data: Country? = null
        private var binding: AdapterCountriesOnlineListItemBinding = DataBindingUtil.bind(view)!!
        fun bindCardData(boardData: Country, pos: Int) {
            this.data = boardData
            binding.country = boardData
           loadImage(boardData.flag!!,view.iv_country_online_flag_item)
            view.tag = pos
        }

        init {
            v.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            var pos = v!!.tag;
            (deshboardFragment as CountriesOnlineFragment).viewCountryDetail(cardData!![pos as Int]!!)
        }
        private fun loadImage(path:String,imageView: ImageView)
        {


            SvgLoader.pluck()
                .with(deshboardFragment.activity)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(path, imageView)


        }




    }



}