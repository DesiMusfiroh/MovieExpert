package com.example.moviecatalogue.ui.detail.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.moviecatalogue.utils.DataDummy

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            for (movie in DataDummy.generateDummyMovies()) {
                if (movie.id == movieId) populateMovie(movie)
            }
        }
    }

    private fun populateMovie(movie: CatalogueEntity) {
        binding.tvName.text = movie.name
        binding.tvDesc.text = movie.desc

        Glide.with(this)
            .load(movie.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imgPoster)

        Glide.with(this)
                .load(movie.backdrop)
                .transform(RoundedCorners(20))
                .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                .into(binding.imgBackdrop)
    }
}