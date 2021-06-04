package com.made.movieexpert.ui.favorite.movie

import FavoriteMovieAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.made.movieexpert.databinding.FragmentMovieBinding
import com.made.movieexpert.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
            val adapter = FavoriteMovieAdapter()

            fragmentMovieBinding.apply {
                rvMovie.layoutManager = LinearLayoutManager(context)
                rvMovie.setHasFixedSize(false)
                rvMovie.adapter = adapter
            }

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, {
                Log.d("favorite", "fragment $it")
                fragmentMovieBinding.progressBar.visibility = View.GONE
                adapter.setMovies(it)
                adapter.notifyDataSetChanged()
            })
        }
    }
}