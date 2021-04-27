package com.example.moviecatalogue.data.source.remote.response

import com.example.moviecatalogue.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@SerializedName("results")
	val results: MutableList<Movie>
)
