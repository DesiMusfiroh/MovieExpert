package com.made.movieexpert.ui.detail.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.capstone.movieexpert.core.domain.model.Movie
import com.capstone.movieexpert.core.utils.Constants.BACKDROP_URL
import com.capstone.movieexpert.core.utils.Constants.POSTER_URL
import com.made.movieexpert.R
import com.made.movieexpert.databinding.ActivityDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener{
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var detailMovie: Movie
    private var menu: Menu? = null
    private var movieId: Int = 0
    private var statusFavorite by Delegates.notNull<Boolean>()
    private val viewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnShare.setOnClickListener(this)

        detailMovie = intent.getParcelableExtra(EXTRA_MOVIE)!!
        populateMovie(detailMovie)
    }

    private fun populateMovie(movie: Movie?) {
        binding.tvName.text = movie?.name
        binding.tvDesc.text = movie?.desc
        binding.tvDate.text = movie?.date
        binding.tvRating.text = StringBuilder("${movie?.rating}/10")
        binding.tvPopularity.text = movie?.popularity.toString()

        Glide.with(this)
            .load(POSTER_URL + movie?.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imgPoster)

        Glide.with(this)
            .load(BACKDROP_URL + movie?.backdrop)
            .transform(RoundedCorners(20))
            .apply(
                 RequestOptions.placeholderOf(R.drawable.ic_loading)
                     .error(R.drawable.ic_error))
            .into(binding.imgBackdrop)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT,  getString(R.string.url_movie, movieId))
                startActivity(Intent.createChooser(shareIntent, getString(R.string.send)))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        statusFavorite = detailMovie.isFavorite
        setFavoriteState(statusFavorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            statusFavorite = !statusFavorite
            viewModel.setFavorite(detailMovie, statusFavorite)
            setFavoriteState(statusFavorite)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_on)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_off)
        }
    }
}