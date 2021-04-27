package com.example.moviecatalogue.data.source.remote.response

import com.example.moviecatalogue.data.model.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowResponse (
    @SerializedName("results")
    val results: MutableList<TvShow>
)