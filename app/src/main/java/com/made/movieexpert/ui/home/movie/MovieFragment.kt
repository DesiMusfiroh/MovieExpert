package com.made.movieexpert.ui.home.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.made.movieexpert.core.data.source.Resource
import com.made.movieexpert.core.domain.model.Movie
import com.made.movieexpert.core.ui.MovieAdapter
import com.made.movieexpert.databinding.FragmentMovieBinding
import com.made.movieexpert.ui.detail.movie.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    val intent =  Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data)
                    context?.startActivity(intent)
                }
            })

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> fragmentMovieBinding.progressBar.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            movieAdapter.setMovies(movies.data)
                        }
                        is Resource.Error -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(false)
                adapter = movieAdapter
            }
        }
    }
}