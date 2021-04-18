package com.example.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatalogueEntity(
        var id: Int,
        var name: String,
        var desc: String,
        var poster: String,
        var backdrop: String,
        var date: String,
        var popularity: Double,
        var rating: Double
) : Parcelable