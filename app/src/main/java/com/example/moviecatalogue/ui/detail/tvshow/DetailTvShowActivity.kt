package com.example.moviecatalogue.ui.detail.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.ui.home.HomeActivity
import com.example.moviecatalogue.utils.Constants.BACKDROP_URL
import com.example.moviecatalogue.utils.Constants.POSTER_URL
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var binding: ActivityDetailBinding
    private var tvShowId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnBack.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)

        val seasonAdapter = SeasonAdapter()
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            tvShowId = extras.getInt(EXTRA_TV_SHOW)
            binding.progressBar.visibility = View.VISIBLE

            viewModel.setSelectedTvShow(tvShowId)
            viewModel.getSeasons().observe(this, {
                binding.progressBar.visibility = View.GONE
                seasonAdapter.setSeasons(it)
            })
            viewModel.getTvShow().observe(this, { populateTvShow(it) })
        }
        with(binding.rvSeason) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailTvShowActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = seasonAdapter
        }
    }

    private fun populateTvShow(tvShow: TvShow) {
        binding.tvName.text = tvShow.name
        binding.tvDesc.text = tvShow.desc
        binding.tvDate.text = tvShow.date
        binding.tvRating.text = StringBuilder("${tvShow.rating}/10")
        binding.tvPopularity.text = tvShow.popularity.toString()

        Glide.with(this)
            .load(POSTER_URL + tvShow.poster)
            .transform(RoundedCorners(20))
            .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
            .into(binding.imgPoster)

        Glide.with(this)
            .load(BACKDROP_URL + tvShow.backdrop)
            .transform(RoundedCorners(20))
            .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
            .into(binding.imgBackdrop)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            R.id.btn_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.url_tv, tvShowId))
                startActivity(Intent.createChooser(shareIntent, getString(R.string.send)))
            }
        }
    }
}