package com.project.countries.ui.adaptor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.countries.R
import kotlinx.android.synthetic.main.no_item.view.*


/**
 * Created by Geeta on 04/02/19.
 */
class NoDataAdaptor(val noDataCaption: String) : RecyclerView.Adapter<NoDataAdaptor.NoDataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoDataHolder {
       val inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.no_item, parent, false);
        return NoDataHolder(inflatedView, noDataCaption)
    }

    override fun onBindViewHolder(holder: NoDataHolder, position: Int) {
        holder.bindCardData()
    }

    override fun getItemCount(): Int {
        return 1
    }

    class NoDataHolder(v: View, val noDataCaption: String) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bindCardData() {
            view.no_data_text.text = noDataCaption

        }
    }


}