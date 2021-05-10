package com.example.moviecatalogue.ui.home.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private val movieAdapter = MovieAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, {movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            movieAdapter.setMovies(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: MovieEntity) {
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