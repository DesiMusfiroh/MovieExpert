package com.made.movieexpert.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
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

    @field:SerializedName("seasons")
    val season: List<Season>?
) : Parcelable

@Parcelize
data class Season(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("season_number")
    val number: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("air_date")
    val date: String,

    @field:SerializedName("overview")
    val desc: String,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("episode_count")
    val episode: Int
) : Parcelable