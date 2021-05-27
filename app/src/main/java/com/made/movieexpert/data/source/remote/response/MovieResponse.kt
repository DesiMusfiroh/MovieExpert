package com.made.movieexpert.data.source.remote.response

import com.made.movieexpert.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@SerializedName("results")
	val results: MutableList<Movie>
)
