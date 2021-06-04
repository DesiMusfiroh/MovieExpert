package com.made.movieexpert.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("results")
    val results: List<TvShowResponse>
)