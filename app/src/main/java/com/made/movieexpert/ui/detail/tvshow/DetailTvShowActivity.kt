package com.made.movieexpert.ui.detail.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.made.movieexpert.R
import com.made.movieexpert.databinding.ActivityDetailTvshowBinding
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.utils.Constants.BACKDROP_URL
import com.made.movieexpert.utils.Constants.POSTER_URL
import com.made.movieexpert.viewmodel.ViewModelFactory
import kotlin.properties.Delegates

class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var binding: ActivityDetailTvshowBinding
    private lateinit var viewModel: DetailTvShowViewModel
    private lateinit var detailTvShow: TvShow
    private lateinit var seasonAdapter: SeasonAdapter
    private var menu: Menu? = null
    private var tvShowId: Int = 0
    private var statusFavorite by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvshowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnShare.setOnClickListener(this)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        detailTvShow = intent.getParcelableExtra(EXTRA_TV_SHOW)!!
        populateTvShow(detailTvShow)

//        seasonAdapter = SeasonAdapter()
//        viewModel.getSeasons().observe(this, {
//            binding.progressBar.visibility = View.GONE
//            seasonAdapter.setSeasons(it)
//        })
//
//        with(binding.rvSeason) {
//            isNestedScrollingEnabled = false
//            layoutManager = LinearLayoutManager(this@DetailTvShowActivity, LinearLayoutManager.HORIZONTAL, false)
//            setHasFixedSize(true)
//            adapter = seasonAdapter
//        }
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
            R.id.btn_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.url_tv, tvShowId))
                startActivity(Intent.createChooser(shareIntent, getString(R.string.send)))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        statusFavorite = detailTvShow.isFavorite
        setFavoriteState(statusFavorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            statusFavorite = !statusFavorite
            viewModel.setFavorite(detailTvShow, statusFavorite)
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