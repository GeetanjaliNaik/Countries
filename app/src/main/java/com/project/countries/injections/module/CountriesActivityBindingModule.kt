package com.project.countries.injections.module

import com.project.countries.injections.scopes.PerActivity
import com.project.countries.ui.screens.deshboard.CountriesDeshboardActivity
import com.project.countries.ui.screens.deshboard.CountriesDeshboardModule
import com.project.countries.ui.screens.details.CountryDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 * Created by Geeta on 04/02/19.
 */
@Module
abstract class CountriesActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(CountriesDeshboardModule::class))
    abstract fun bindCountriesDeshboardActivity(): CountriesDeshboardActivity

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindCountryDetailActivity(): CountryDetailActivity

}