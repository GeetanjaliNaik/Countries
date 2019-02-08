package com.project.countries.injections

import android.app.Application
import com.project.countries.CountriesApplication
import com.project.countries.injections.module.CountriesActivityBindingModule
import com.project.countries.injections.module.CountriesApplicationModule
import com.project.countries.injections.scopes.PerApplication
import com.project.countries.network.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
/**
 * Created by Geeta on 04/02/19.
 */
@PerApplication
@Singleton
@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class, CountriesApplicationModule::class,
    CountriesActivityBindingModule::class, NetworkModule::class))
interface CountriesAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CountriesAppComponent
    }

    fun inject(app: CountriesApplication)
}