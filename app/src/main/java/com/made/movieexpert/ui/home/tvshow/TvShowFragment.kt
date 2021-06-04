package com.made.movieexpert.ui.home.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.made.movieexpert.core.data.source.Resource
import com.made.movieexpert.databinding.FragmentTvShowBinding
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