package com.example.moviecatalogue.ui.detail.tvshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.ui.home.HomeActivity

class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnBack.setOnClickListener(this)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TV_SHOW)
            viewModel.setSelectedTvShow(tvShowId)
            populateTvShow(viewModel.getTvShow())
        }
    }

    private fun populateTvShow(tvShow: CatalogueEntity) {
        binding.tvName.text = tvShow.name
        binding.tvDesc.text = tvShow.desc
        binding.tvDate.text = tvShow.date
        binding.tvRating.text = StringBuilder("${tvShow.rating} /10")
        binding.tvPopularity.text = tvShow.popularity.toString()

        Glide.with(this)
            .load(tvShow.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(binding.imgPoster)

        Glide.with(this)
            .load(tvShow.backdrop)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(binding.imgBackdrop)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back -> { startActivity(Intent(this, HomeActivity::class.java)) }
        }
    }
}