package com.made.movieexpert.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_name")
    val name: String,

    @field:SerializedName("overview")
    val desc: String,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("backdrop_path")
    val backdrop: String,

    @field:SerializedName("first_air_date")
    val date: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val rating: Double,

) : Parcelable
