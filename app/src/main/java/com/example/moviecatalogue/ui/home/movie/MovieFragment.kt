package com.example.moviecatalogue.ui.home.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.example.moviecatalogue.ui.home.HomeAdapter

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = viewModel.getMovies()

            val movieAdapter = HomeAdapter()
            movieAdapter.setCatalogues(movies)

            movieAdapter.setOnItemClickCallback(object : HomeAdapter.OnItemClickCallback {
                override fun onItemClicked(data: CatalogueEntity) {
                    Intent(activity, DetailMovieActivity::class.java).also {
                        it.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.id)
                        startActivity(it)
                    }
                }
            })
            with(fragmentMovieBinding.rvMovie) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}