package com.example.moviecatalogue.ui.home.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.ui.home.CatalogueAdapter
import com.example.moviecatalogue.utils.DataDummy

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movies = DataDummy.generateDummyMovies()
            val catalogueAdapter = CatalogueAdapter()
            catalogueAdapter.setCatalogues(movies)
            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = catalogueAdapter
            }
        }
    }
}