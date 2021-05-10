package com.example.moviecatalogue.ui.home.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private val tvShowAdapter = TvShowAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShows().observe(viewLifecycleOwner, {
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setTvShows(it)
            })

            tvShowAdapter.setOnItemClickCallback(object : TvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShow) {
                    Intent(activity, DetailTvShowActivity::class.java).also {
                        it.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data.id)
                        startActivity(it)
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvshow) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}