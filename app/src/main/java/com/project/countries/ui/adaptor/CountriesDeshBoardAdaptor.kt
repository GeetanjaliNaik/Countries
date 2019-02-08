package com.project.countries.ui.adaptor

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.project.countries.ui.screens.deshboard.offline.CountriesOfflineFragment
import com.project.countries.ui.screens.deshboard.online.CountriesOnlineFragment
/**
 * Created by Geeta on 04/02/19.
 */
class CountriesDeshBoardAdaptor (fragmentManager: FragmentManager, private val pager: Array<String>) :
    FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 ->
                return CountriesOnlineFragment.newInstance()
            1 ->
                return CountriesOfflineFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return pager.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pager.get(position)
    }

}