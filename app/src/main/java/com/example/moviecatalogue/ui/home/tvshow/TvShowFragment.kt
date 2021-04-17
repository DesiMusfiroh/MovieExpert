package com.example.moviecatalogue.ui.home.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.ui.home.CatalogueAdapter
import com.example.moviecatalogue.utils.DataDummy

class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShows = DataDummy.generateDummyTvShows()
            val catalogueAdapter = CatalogueAdapter()
            catalogueAdapter.setCatalogues(tvShows)
            with(fragmentTvShowBinding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = catalogueAdapter
            }
        }
    }
}