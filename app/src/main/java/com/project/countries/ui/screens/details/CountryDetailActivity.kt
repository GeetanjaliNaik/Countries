package com.project.countries.ui.screens.details

import android.Manifest
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.project.countries.R
import com.project.countries.databinding.ActivityCountryDetailsLayoutBinding
import com.project.countries.ui.uibase.BaseActivity
import com.project.countries.utiles.AppData
import kotlinx.android.synthetic.main.activity_country_details_layout.*
import javax.inject.Inject
import com.project.countries.databinding.ActivityCountryDetailOfflineLayoutBinding
import com.project.countries.ui.screens.deshboard.CountriesDeshboardActivity
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import com.project.countries.utiles.AppUtils
import com.project.countries.utiles.svg.svgurl.SvgLoader
import kotlinx.android.synthetic.main.activity_country_detail_offline_layout.*
import java.io.File


/**
 * Created by Geeta on 04/02/19.
 */
class CountryDetailActivity : BaseActivity(),CountryDetailActContract.View {



    @Inject
    lateinit var appData: AppData
    @Inject
    lateinit var countryDetailPresenter: CountryDetailActPresenter
    lateinit var toolbar: Toolbar;
    private var mCountryonlineBind: ActivityCountryDetailsLayoutBinding? = null
    private var mCountryOfflinBind: ActivityCountryDetailOfflineLayoutBinding? = null
    var screenCode:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_country_details_layout)

        screenCode=intent.getIntExtra("screenCode",1)
        if(screenCode== CountriesDeshboardActivity.DeshboardConstant.ONLINE_DETAIL) {
            mCountryonlineBind = DataBindingUtil.setContentView(this, R.layout.activity_country_details_layout);
            mCountryonlineBind!!.country = appData.country

        }
        else if(screenCode== CountriesDeshboardActivity.DeshboardConstant.OFFLINE_DETAIL)
        {
            mCountryOfflinBind = DataBindingUtil.setContentView(this, R.layout.activity_country_detail_offline_layout);
            mCountryOfflinBind!!.countrydb=appData.countryDb
        }
        toolbar=findViewById(R.id.toolbar);
        initToolbar()

        countryDetailPresenter.attachView(this)
//        collaps_toolbar_layout.title=appData.country!!.name
        if(screenCode== CountriesDeshboardActivity.DeshboardConstant.ONLINE_DETAIL)
        fab_save.setOnClickListener { view ->
            val isSDPresent = android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED

            if(isSDPresent) {
                if (AppUtils.availableEXTMemorySpace() <= 1)
                    showAlert(getString(R.string.external_memory_less_space))
                else
                    checkWritingPermission()
            }
            else
            {
                countryDetailPresenter.saveCountryData(appData.country!!)
            }

        }
    }

    override fun onResume() {
        super.onResume()
        if(screenCode== CountriesDeshboardActivity.DeshboardConstant.ONLINE_DETAIL) {
//            countryDetailPresenter.loadImage(appData.country!!.flag!!, iv_country_flag)
            SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(appData.country!!.flag!!, iv_country_online_detail_flag)
        }
        else
        {
//            loadSdcardImage(appData.countryDb!!.flag!!)
            SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .loadfile(File(appData.countryDb!!.flag!!), iv_country_offline_flag)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        SvgLoader.pluck().close()
        appData.country=null
        appData.countryDb=null
    }
    override fun busInputReceived(busModal: Any?) {

    }
    private fun initToolbar() {
//        toolbar.toolbar_notification.visibility= View.GONE
        setSupportActionBar(toolbar)
        if(screenCode== CountriesDeshboardActivity.DeshboardConstant.ONLINE_DETAIL) {
//            supportActionBar?.setTitle(appData.country!!.name)
            collapsing_toolbar_online.title=appData.country!!.name
        }
        else {
//            supportActionBar?.setTitle(appData.countryDb!!.name)
            collapsing_toolbar_offline.title=appData.countryDb!!.name
        }


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
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
    override fun updateSaveUI(message: String) {

        showAlert(message)

//        DBHandler.getInstance().getAll(CountryDbData::class.java,this)
    }


    private fun checkWritingPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
        }
        else
        {
            countryDetailPresenter.saveCountryData(appData.country!!)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 100) {
            if (grantResults.size >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted
                countryDetailPresenter.saveCountryData(appData.country!!)
            } else {
                // permission wasn't granted
                showAlert("With out this permision we unable to save data")
                return
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
                onBackPressed()
            return true;
        }
        return false
    }
}
