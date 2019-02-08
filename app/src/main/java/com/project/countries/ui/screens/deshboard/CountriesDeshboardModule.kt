package com.project.countries.ui.screens.deshboard

import com.project.countries.injections.scopes.PerFragment
import com.project.countries.ui.screens.deshboard.offline.CountriesOfflineFragContract
import com.project.countries.ui.screens.deshboard.offline.CountriesOfflineFragPresenter
import com.project.countries.ui.screens.deshboard.offline.CountriesOfflineFragment
import com.project.countries.ui.screens.deshboard.online.CountriesOnlineFragContract
import com.project.countries.ui.screens.deshboard.online.CountriesOnlineFragPresenter
import com.project.countries.ui.screens.deshboard.online.CountriesOnlineFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 * Created by Geeta on 04/02/19.
 */
@Module
abstract class CountriesDeshboardModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun providCountriesOnlineFrag(): CountriesOnlineFragment

    @PerFragment
    @Binds
    abstract fun provideCountriesOnlinePresenter(presnDash: CountriesOnlineFragPresenter): CountriesOnlineFragContract.Presenter

    @PerFragment
    @ContributesAndroidInjector
    abstract fun providCountriesOfflineFrag(): CountriesOfflineFragment

    @PerFragment
    @Binds
    abstract fun provideCountriesOfflinePresenter(presnDash: CountriesOfflineFragPresenter): CountriesOfflineFragContract.Presenter
}