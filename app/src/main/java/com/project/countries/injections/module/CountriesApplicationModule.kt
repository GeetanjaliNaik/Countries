package com.project.countries.injections.module

import android.app.Application
import com.project.countries.CountriesApplication
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
/**
 * Created by Geeta on 04/02/19.
 */
@Module(includes = arrayOf(AndroidInjectionModule::class))
public abstract class CountriesApplicationModule {
    @Binds
    @Singleton
    internal abstract fun application(app: CountriesApplication): Application
}