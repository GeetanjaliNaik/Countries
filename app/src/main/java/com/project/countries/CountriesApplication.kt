package com.project.countries

import android.app.Activity
import android.app.Service
import android.content.Context
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import com.facebook.stetho.Stetho
import com.project.countries.database.DBHandler
import com.project.countries.injections.DaggerCountriesAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.realm.Realm
import javax.inject.Inject
/**
 * Created by Geeta on 04/02/19.
 */
public class CountriesApplication  : MultiDexApplication(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>


    //    lateinit var mFirebaseRemoteConfig: FirebaseRemoteConfig
    override fun onCreate() {
        super.onCreate()
        buildComponent();
        sContext = this
        initDB()
        Realm.init(this)
//        setupTimber();
        setUpStethoInspector()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }




   override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceDispatchingAndroidInjector
    }


    private fun buildComponent() {
        DaggerCountriesAppComponent.builder()
            .application(this)
            .build().inject(this)

    }

    private fun initDB() {
        DBHandler.getInstance().initRealm(this)
    }

    companion object {
        var sContext: Context? = null
        fun getApplicationContext(): Context {
            return sContext!!
        }
    }
    private fun setUpStethoInspector() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)).build())
        }
    }
}