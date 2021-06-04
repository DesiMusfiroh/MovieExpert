package com.made.movieexpert.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val desc: String,
    val poster: String,
    val backdrop: String,
    val date: String,
    val popularity: Double,
    val rating: Double,
    val isFavorite: Boolean,
) : Parcelable
