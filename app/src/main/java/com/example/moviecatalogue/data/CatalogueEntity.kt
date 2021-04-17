package com.example.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatalogueEntity(
    var id: String,
    var name: String,
    var desc: String,
    var poster: String,
    var img_preview: String
) : Parcelable