package com.example.moviecatalogue.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.example.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.example.moviecatalogue.ui.home.movie.MovieAdapter
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
    private lateinit var fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (activity != null) {
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
//
//            val adapter = MovieAdapter()
//            fragmentFavoriteMovieBinding?.progressBar?.visibility = View.VISIBLE
//            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, {
//                fragmentFavoriteMovieBinding?.progressBar?.visibility = View.GONE
//                adapter.setMovies(it)
//                adapter.notifyDataSetChanged()
//            })
//
//            fragmentFavoriteMovieBinding.apply {
//                rvMovie.layoutManager = LinearLayoutManager(context)
//                rvMovie.setHasFixedSize(true)
//                rvMovie.adapter = adapter
//            }
//        }




//        viewModel = ViewModelProvider(this).get(FavoriteMovieViewModel::class.java)
//
//        val movieAdapter = MovieAdapter()
//        fragmentFavoriteMovieBinding.progressBar.visibility = View.VISIBLE
//
//        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: MovieEntity) {
//                Intent(activity, DetailMovieActivity::class.java).also {
//                    it.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.id)
//                    startActivity(it)
//                }
//            }
//        })
//
//        fragmentFavoriteMovieBinding.apply {
//            rvMovie.layoutManager = GridLayoutManager(context, 3)
//            rvMovie.setHasFixedSize(true)
//            rvMovie.adapter = movieAdapter
//        }
//
//        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer {
//            movieAdapter.setMovies(it)
//            fragmentFavoriteMovieBinding.progressBar.visibility = View.GONE
//        })

    }
}