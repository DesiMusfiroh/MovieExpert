package com.capstone.movieexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val name: String,
    val desc: String,
    val poster: String,
    val backdrop: String,
    val date: String,
    val popularity: Double,
    val rating: Double,
    val isFavorite: Boolean
) : Parcelable