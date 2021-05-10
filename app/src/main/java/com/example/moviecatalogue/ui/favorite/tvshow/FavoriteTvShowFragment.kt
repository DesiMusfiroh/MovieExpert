package com.example.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.ui.home.tvshow.TvShowAdapter

class FavoriteTvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private val tvShowAdapter = TvShowAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}