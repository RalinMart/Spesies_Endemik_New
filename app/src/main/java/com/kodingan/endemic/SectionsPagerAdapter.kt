  package com.kodingan.endemic

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.kodingan.endemic.home.HomeFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.menu_home, R.string.menu_favorite)
    }

    private val FavoriteFragment = Class.forName("com.kodingan.endemic.favorite.FavoriteFragment").newInstance() as Fragment



    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> HomeFragment()
                1 -> FavoriteFragment
                else -> Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}