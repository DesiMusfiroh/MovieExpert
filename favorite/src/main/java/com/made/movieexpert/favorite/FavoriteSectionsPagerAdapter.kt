package com.made.movieexpert.favorite

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.made.movieexpert.R
import com.made.movieexpert.favorite.movie.FavoriteMovieFragment
import com.made.movieexpert.favorite.tvshow.FavoriteTvShowFragment

@Suppress("DEPRECATION")
class FavoriteSectionsPagerAdapter (private val mContext: FavoriteFragment, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tvshow)
    }

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> FavoriteMovieFragment()
                1 -> FavoriteTvShowFragment()
                else -> Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2
}
