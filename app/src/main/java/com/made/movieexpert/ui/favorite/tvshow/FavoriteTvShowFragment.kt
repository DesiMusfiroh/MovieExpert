package com.made.movieexpert.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.made.movieexpert.databinding.FragmentTvShowBinding
import com.made.movieexpert.viewmodel.ViewModelFactory

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
            val adapter = FavoriteTvShowAdapter()

            fragmentTvShowBinding.apply {
                rvTvshow.layoutManager = LinearLayoutManager(context)
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