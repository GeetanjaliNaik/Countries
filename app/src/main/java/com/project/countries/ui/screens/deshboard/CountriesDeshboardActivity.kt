package com.project.countries.ui.screens.deshboard

import android.os.Bundle
import com.project.countries.ui.adaptor.CountriesDeshBoardAdaptor
import com.project.countries.ui.uibase.BaseActivity
import com.project.countries.utiles.AppData
import javax.inject.Inject
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import com.project.countries.R
import com.project.countries.utiles.PreferenceUtil
/**
 * Created by Geeta on 04/02/19.
 */
class CountriesDeshboardActivity : BaseActivity() {
    object DeshboardConstant {
        val ONLINE_DETAIL = 1
        val OFFLINE_DETAIL = 2
    }

    @Inject
    lateinit var appData: AppData
    lateinit var toolbar: Toolbar;
    lateinit var viewPager: ViewPager
    lateinit var todoTabLayout: TabLayout
    var list: Array<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_deshboard_layout)
        toolbar=findViewById(R.id.toolbar);
        viewPager=findViewById(R.id.todo_view_pager)
        todoTabLayout=findViewById(R.id.tabs)
        initToolbar()
        list= arrayOf(getString(R.string.online),getString(R.string.offline))
        setAdapator()
        /* supportFragmentManager.inTransaction {
             add(R.id.container_body, BaifDeshboardFragment.newInstance(), "BAIF")
         }*/

    }
    private fun initToolbar() {
//        toolbar.toolbar_notification.visibility= View.GONE
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(resources.getString(R.string.app_name))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        supportFragmentManager.addOnBackStackChangedListener {
            val stackHeight = supportFragmentManager.backStackEntryCount
            if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
                supportActionBar!!.setHomeButtonEnabled(true)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                supportActionBar!!.setHomeButtonEnabled(false)
                finish()
            }
        }
    }
    override fun onBackPressed() {
        return
    }

    fun setAdapator()
    {
        val countriesFragmentPagerAdapter = CountriesDeshBoardAdaptor(supportFragmentManager, list!!)
        viewPager.setAdapter(countriesFragmentPagerAdapter)
        viewPager.setOffscreenPageLimit(2)
        todoTabLayout.setupWithViewPager(viewPager)
        var isNetwork =PreferenceUtil!!.newInstance().get(this,"INTERNET",false)
        if(isNetwork!!)
            viewPager.currentItem=0
        else
            viewPager.currentItem=1
    }
    override fun busInputReceived(busModal: Any?) {

    }
}