package com.example.moviecatalogue.ui.detail.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.example.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.example.moviecatalogue.utils.DataDummy

class DetailTvShowActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var binding: ActivityDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TV_SHOW)
            for (tvShow in DataDummy.generateDummyTvShows()) {
                if (tvShow.id == tvShowId) populateTvShow(tvShow)
            }
        }
    }

    private fun populateTvShow(tvShow: CatalogueEntity) {
        binding.tvName.text = tvShow.name
        binding.tvDesc.text = tvShow.desc

        Glide.with(this)
            .load(tvShow.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(binding.imgPoster)
    }
}