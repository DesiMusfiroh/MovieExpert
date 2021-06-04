package com.made.movieexpert.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeasonResponse(
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