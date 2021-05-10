package com.example.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movieentities")
data class MovieEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "movieId")
        val id: Int,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "desc")
        val desc: String,

        @ColumnInfo(name = "poster")
        val poster: String,

        @ColumnInfo(name = "backdrop")
        val backdrop: String,

        @ColumnInfo(name = "date")
        val date: String,

        @ColumnInfo(name = "popularity")
        val popularity: Double,

        @ColumnInfo(name = "rating")
        val rating: Double,

        @ColumnInfo(name = "favorited")
        var favorited: Boolean = false,
) : Parcelable