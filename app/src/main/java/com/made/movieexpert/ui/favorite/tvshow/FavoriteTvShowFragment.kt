package com.made.movieexpert.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.movieexpert.core.domain.model.TvShow
import com.capstone.movieexpert.core.ui.FavoriteTvShowAdapter
import com.capstone.movieexpert.core.ui.TvShowAdapter
import com.made.movieexpert.databinding.FragmentTvShowBinding
import com.made.movieexpert.ui.detail.tvshow.DetailTvShowActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private val viewModel: FavoriteTvShowViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val adapter = FavoriteTvShowAdapter()
            adapter.setOnItemClickCallback(object : FavoriteTvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShow) {
                    val intent =  Intent(context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data)
                    context?.startActivity(intent)
                }
            })
            fragmentTvShowBinding.apply {
                rvTvshow.layoutManager = LinearLayoutManager(context)
                rvTvshow.setHasFixedSize(false)
                rvTvshow.adapter = adapter
            }

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.favoriteTvShows.observe(viewLifecycleOwner, {
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                adapter.setTvShows(it)
                adapter.notifyDataSetChanged()
            })
        }
    }
}