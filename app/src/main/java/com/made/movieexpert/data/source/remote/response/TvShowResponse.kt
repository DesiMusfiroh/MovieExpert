package com.made.movieexpert.data.source.remote.response

import com.made.movieexpert.data.model.TvShowRes
import com.google.gson.annotations.SerializedName

data class TvShowResponse (
    @SerializedName("results")
    val results: MutableList<TvShowRes>
)