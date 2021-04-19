package com.example.moviecatalogue.ui.detail.movie

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

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener{
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnBack.setOnClickListener(this)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            viewModel.setSelectedMovie(movieId)
            populateMovie(viewModel.getMovie())
        }
    }

    private fun populateMovie(movie: CatalogueEntity) {
        binding.tvName.text = movie.name
        binding.tvDesc.text = movie.desc
        binding.tvDate.text = movie.date
        binding.tvRating.text = StringBuilder("${movie.rating}/10")
        binding.tvPopularity.text = movie.popularity.toString()

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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back -> { startActivity(Intent(this, HomeActivity::class.java)) }
        }
    }
}