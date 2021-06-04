package com.made.movieexpert.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.made.movieexpert.core.domain.model.Movie
import com.made.movieexpert.core.ui.FavoriteMovieAdapter
import com.made.movieexpert.databinding.FragmentMovieBinding
import com.made.movieexpert.ui.detail.movie.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val adapter = FavoriteMovieAdapter()
            adapter.setOnItemClickCallback(object : FavoriteMovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    val intent =  Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data)
                    context?.startActivity(intent)
                }
            })

            fragmentMovieBinding.apply {
                rvMovie.layoutManager = LinearLayoutManager(context)
                rvMovie.setHasFixedSize(false)
                rvMovie.adapter = adapter
            }

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.favoriteMovies.observe(viewLifecycleOwner, {
                Log.d("favorite", "fragment $it")
                fragmentMovieBinding.progressBar.visibility = View.GONE
                adapter.setMovies(it)
                adapter.notifyDataSetChanged()
            })
        }
    }
}