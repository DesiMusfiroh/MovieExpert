package com.made.movieexpert.data.source.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieRes(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val name: String,

    @field:SerializedName("overview")
    val desc: String,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("backdrop_path")
    val backdrop: String,

    @field:SerializedName("release_date")
    val date: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val rating: Double
) : Parcelable