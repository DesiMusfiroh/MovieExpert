package com.example.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.ui.home.tvshow.TvShowAdapter
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            val adapter = TvShowAdapter()

            fragmentTvShowBinding.apply {
                rvTvshow.layoutManager = GridLayoutManager(context, 3)
                rvTvshow.setHasFixedSize(false)
                rvTvshow.adapter = adapter
            }

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, {
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            })
        }
    }
}