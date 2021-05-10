package com.example.moviecatalogue.ui.detail.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.moviecatalogue.ui.home.HomeActivity
import com.example.moviecatalogue.utils.Constants.BACKDROP_URL
import com.example.moviecatalogue.utils.Constants.POSTER_URL
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener{
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    private lateinit var binding: ActivityDetailMovieBinding
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnBack.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            movieId = extras.getInt(EXTRA_MOVIE)
            viewModel.setSelectedMovie(movieId)
            viewModel.getMovie.observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> Toast.makeText(applicationContext, "Loading", Toast.LENGTH_SHORT).show()
                        Status.SUCCESS -> if (movie.data != null) {
                            populateMovie(movie.data)
                        }
                        Status.ERROR -> Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun populateMovie(movie: MovieEntity) {
        binding.tvName.text = movie.name
        binding.tvDesc.text = movie.desc
        binding.tvDate.text = movie.date
        binding.tvRating.text = StringBuilder("${movie.rating}/10")
        binding.tvPopularity.text = movie.popularity.toString()

        Glide.with(this)
            .load(POSTER_URL + movie.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imgPoster)

        Glide.with(this)
            .load(BACKDROP_URL + movie.backdrop)
            .transform(RoundedCorners(20))
            .apply(
                 RequestOptions.placeholderOf(R.drawable.ic_loading)
                     .error(R.drawable.ic_error))
            .into(binding.imgBackdrop)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back -> { startActivity(Intent(this, HomeActivity::class.java)) }
            R.id.btn_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT,  getString(R.string.url_movie, movieId))
                startActivity(Intent.createChooser(shareIntent, getString(R.string.send)))
            }
        }
    }
}