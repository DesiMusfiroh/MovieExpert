package com.made.movieexpert.ui.home.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.movieexpert.core.data.source.Resource
import com.capstone.movieexpert.core.domain.model.Movie
import com.capstone.movieexpert.core.domain.model.TvShow
import com.capstone.movieexpert.core.ui.MovieAdapter
import com.capstone.movieexpert.core.ui.TvShowAdapter
import com.made.movieexpert.databinding.FragmentTvShowBinding
import com.made.movieexpert.ui.detail.movie.DetailMovieActivity
import com.made.movieexpert.ui.detail.tvshow.DetailTvShowActivity
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private val tvShowAdapter = TvShowAdapter()
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            tvShowAdapter.setOnItemClickCallback(object : TvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShow) {
                    val intent =  Intent(context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data)
                    context?.startActivity(intent)
                }
            })

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.tvShows.observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    when (tvShows) {
                        is Resource.Loading -> fragmentTvShowBinding.progressBar.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            tvShowAdapter.setTvShows(tvShows.data)
                        }
                        is Resource.Error -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvshow) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(false)
                adapter = tvShowAdapter
            }
        }
    }
}