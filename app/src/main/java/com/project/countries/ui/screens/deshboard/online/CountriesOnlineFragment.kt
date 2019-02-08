package com.project.countries.ui.screens.deshboard.online

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextWatcher
import android.view.*
import com.project.countries.R
import com.project.countries.data.remote.module.countries.Country
import com.project.countries.databinding.FragmentCountriesOnlineLayoutBinding
import com.project.countries.ui.adaptor.CountriesOnlineListAdapter
import com.project.countries.ui.screens.details.CountryDetailActivity
import com.project.countries.ui.uibase.BaseFragment
import com.project.countries.utiles.AppData
import kotlinx.android.synthetic.main.fragment_countries_online_layout.view.*
import java.util.ArrayList
import javax.inject.Inject
import android.text.Editable
import android.util.Log
import com.project.countries.ui.adaptor.NoDataAdaptor
import java.nio.file.Files.delete
import java.nio.file.Files.isDirectory
import com.bumptech.glide.Glide
//import com.bumptech.glide.util.Preconditions
import com.project.countries.ui.screens.deshboard.CountriesDeshboardActivity
//import com.project.countries.utiles.svg.GlideApp


/**
 * Created by Geeta on 04/02/19.
 */
class CountriesOnlineFragment : BaseFragment(), CountriesOnlineFragContract.View{
    companion object {
        fun newInstance(): CountriesOnlineFragment {
            return CountriesOnlineFragment()
        }
    }

    @Inject
    lateinit var countriesOnlinePresenter: CountriesOnlineFragPresenter

    @Inject
    lateinit var appData: AppData
    lateinit var fragView: View
    var mCardAdapter: CountriesOnlineListAdapter? = null
    private var mOnlineBind: FragmentCountriesOnlineLayoutBinding? = null
    var countriesList: ArrayList<Country>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mOnlineBind = DataBindingUtil.inflate(inflater, R.layout.fragment_countries_online_layout, container, false)
        fragView = mOnlineBind!!.root
//        fragView = inflater.inflate(R.layout.fragment_avt_baif_deshboard, container, false)
        countriesOnlinePresenter.attachView(this)
        initFragment()
        setHasOptionsMenu(true)
        return fragView;
    }

    override fun onResume() {
        super.onResume()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(mCardAdapter!=null)
        {
            mCardAdapter!!.setAdapter()
        }
    }
    private fun initFragment() {
        fragView.search_online_et.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") {
                    //do your work here
                    var searchName=fragView.search_online_et.text.toString()
//                clearCache()
                    countriesOnlinePresenter.getCountries(searchName)
                }
            }


            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {

            }

            override fun afterTextChanged(s: Editable) {
//                var searchName=fragView.search_online_et.text.toString()
////                clearCache()
//                countriesOnlinePresenter.getCountries(searchName)
            }
        });
    }

    override fun updateUIforCountries(countriesListResponse: List<Country>) {
        if (countriesListResponse!!?.size!! > 0) {
            countriesList= countriesListResponse!! as ArrayList<Country>?
            mCardAdapter = CountriesOnlineListAdapter(countriesList, this)
            initBeneficiaryRecyclerView(LinearLayoutManager(mContext), mCardAdapter)
            mCardAdapter!!.notifyDataSetChanged()

        } else {
            val mNoDataAdapter = NoDataAdaptor(getString(R.string.no_data_found))
            mOnlineBind!!.countriesOnlineViewRc.setLayoutManager(LinearLayoutManager(context))
            mOnlineBind!!.countriesOnlineViewRc.setAdapter(mNoDataAdapter)
        }
    }



    private fun initBeneficiaryRecyclerView(layoutManager: LinearLayoutManager?, adapter: CountriesOnlineListAdapter?) {
        var recentTransList: RecyclerView = mOnlineBind!!.countriesOnlineViewRc
        recentTransList.setLayoutManager(layoutManager)
        // we expect only fixed sized item for now
        recentTransList.setHasFixedSize(true)
        // sample adapter with random data
        recentTransList.setAdapter(adapter)
        // enable center post scrolling
//        recentTransList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        // enable center post touching on item and item click listener

    }

    fun viewCountryDetail(viewDetail: Country) {
        appData.country = viewDetail
        val intent = Intent(activity, CountryDetailActivity::class.java)
        intent.putExtra("screenCode", CountriesDeshboardActivity.DeshboardConstant.ONLINE_DETAIL)
        activity!!.startActivity(intent)
    }

    /*fun clearCache() {
        Log.w("countriesonline", "clearing cache")
        GlideApp.get(context!!).clearMemory()
        val cacheDir = Preconditions.checkNotNull(GlideApp.getPhotoCacheDir(context!!))
        if (cacheDir.isDirectory()) {
            for (child in cacheDir.listFiles()) {
                if (!child.delete()) {
                    Log.w("countriesonline", "cannot delete: $child")
                }
            }
        }
//        reload()
    }*/
   /* override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.baif_dash_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        val searchViewMenuItem = menu!!.findItem(R.id.search)
        val searchView = searchViewMenuItem.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = resources.getString(R.string.agent_dash_search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //Do your search
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (mCardAdapter != null) {
                    duDiligences = mCardAdapter!!.setFilter(newText)
                    mCardAdapter!!.setList(duDiligences!!)
                }
                return false
            }
        })
    }*/
}