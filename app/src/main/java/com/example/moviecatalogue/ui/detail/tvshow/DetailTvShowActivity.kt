package com.example.moviecatalogue.ui.detail.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.databinding.ActivityDetailTvshowBinding
import com.example.moviecatalogue.ui.home.HomeActivity
import com.example.moviecatalogue.utils.Constants.BACKDROP_URL
import com.example.moviecatalogue.utils.Constants.POSTER_URL
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var binding: ActivityDetailTvshowBinding
    private var tvShowId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvshowBinding.inflate(layoutInflater)
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
            viewModel.getTvShow.observe(this, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> Toast.makeText(applicationContext, "Loading", Toast.LENGTH_SHORT).show()
                        Status.SUCCESS -> if (tvShow.data != null) {
                            populateTvShow(tvShow.data)
                        }
                        Status.ERROR -> Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            viewModel.getSeasons().observe(this, {
                Log.d("season", "activity it $it}")
                binding.progressBar.visibility = View.GONE
                seasonAdapter.setSeasons(it)
            })

        }

        with(binding.rvSeason) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailTvShowActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = seasonAdapter
        }
    }

    private fun populateTvShow(tvShow: TvShowEntity) {
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