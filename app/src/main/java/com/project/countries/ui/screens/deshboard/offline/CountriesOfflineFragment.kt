package com.project.countries.ui.screens.deshboard.offline

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.countries.R
import com.project.countries.data.remote.module.countries.Country
import com.project.countries.database.countries.CountryDbData
import com.project.countries.databinding.FragmentCountriesOfflineLayoutBinding
import com.project.countries.ui.adaptor.CountriesOfflineListAdapter
import com.project.countries.ui.adaptor.CountriesOnlineListAdapter
import com.project.countries.ui.adaptor.NoDataAdaptor
import com.project.countries.ui.screens.deshboard.CountriesDeshboardActivity
import com.project.countries.ui.screens.details.CountryDetailActivity
import com.project.countries.ui.uibase.BaseFragment
import com.project.countries.utiles.AppData
import kotlinx.android.synthetic.main.fragment_countries_offline_layout.view.*
import java.util.ArrayList
import javax.inject.Inject
/**
 * Created by Geeta on 04/02/19.
 */
class CountriesOfflineFragment: BaseFragment(), CountriesOfflineFragContract.View{


    companion object {
        fun newInstance(): CountriesOfflineFragment {
            return CountriesOfflineFragment()
        }
    }

    @Inject
    lateinit var dashBoardOfflinePresenter: CountriesOfflineFragPresenter

    @Inject
    lateinit var appData: AppData
    lateinit var fragView: View
    var mCardAdapter: CountriesOfflineListAdapter? = null
    private var mOfflineBind: FragmentCountriesOfflineLayoutBinding? = null
    var countriesList: ArrayList<CountryDbData>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mOfflineBind = DataBindingUtil.inflate(inflater, R.layout.fragment_countries_offline_layout, container, false)
        fragView = mOfflineBind!!.root
//        fragView = inflater.inflate(R.layout.fragment_avt_baif_deshboard, container, false)
        dashBoardOfflinePresenter.attachView(this)
        initFragment()
        setHasOptionsMenu(true)
        return fragView;
    }

    override fun onResume() {
        super.onResume()
        dashBoardOfflinePresenter.getOfflineCountries()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(mCardAdapter!=null)
        {
            mCardAdapter!!.setAdapter()
        }
    }
    private fun initFragment() {
        fragView.search_offline_et.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") {
                    var searchName=fragView.search_offline_et.text.toString()
                    countriesList=mCardAdapter!!.setFilter(searchName)
                    mCardAdapter!!.setList(countriesList!!)
                }
            }


            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {

            }

            override fun afterTextChanged(s: Editable) {
                var searchName=fragView.search_offline_et.text.toString()
                countriesList=mCardAdapter!!.setFilter(searchName)
                mCardAdapter!!.setList(countriesList!!)
            }
        });
    }

    override fun updateUIforCountries(countryList: List<CountryDbData>?) {
        Log.i("CountryDetailActivity","realmModel:"+countryList!!.size)
        if (countryList!=null&&countryList!!.size!! > 0) {
            fragView.search_offline_et.visibility=View.VISIBLE
            countriesList= countryList as ArrayList<CountryDbData>?
            mCardAdapter = CountriesOfflineListAdapter(countriesList, this)
            initBeneficiaryRecyclerView(LinearLayoutManager(mContext), mCardAdapter)
            mCardAdapter!!.notifyDataSetChanged()
        } else {
            fragView.search_offline_et.visibility=View.GONE
            val mNoDataAdapter = NoDataAdaptor(getString(R.string.no_data_found))
            mOfflineBind!!.countriesOfflineViewRc.setLayoutManager(LinearLayoutManager(context))
            mOfflineBind!!.countriesOfflineViewRc.setAdapter(mNoDataAdapter)
        }
    }



    private fun initBeneficiaryRecyclerView(layoutManager: LinearLayoutManager?, adapter: CountriesOfflineListAdapter?) {
        var recentTransList: RecyclerView = mOfflineBind!!.countriesOfflineViewRc
        recentTransList.setLayoutManager(layoutManager)
        // we expect only fixed sized item for now
        recentTransList.setHasFixedSize(true)
        // sample adapter with random data
        recentTransList.setAdapter(adapter)
        // enable center post scrolling
//        recentTransList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        // enable center post touching on item and item click listener

    }

    fun viewCountryDetail(viewDetail: CountryDbData) {
        appData.countryDb = viewDetail
        val intent = Intent(activity, CountryDetailActivity::class.java)
        intent.putExtra("screenCode", CountriesDeshboardActivity.DeshboardConstant.OFFLINE_DETAIL)
        activity!!.startActivity(intent)
    }

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