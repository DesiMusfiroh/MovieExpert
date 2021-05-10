package com.example.moviecatalogue.data.source.local.entity


import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviecatalogue.data.model.Season
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@Parcelize
//@Entity(tableName = "tvshowentities")
//data class TvShowEntity(
//
//        @PrimaryKey
//        @NonNull
//        @ColumnInfo(name = "tvShowId")
//        val id: Int,
//
//        @ColumnInfo(name = "name")
//        val name: String,
//
//        @ColumnInfo(name = "desc")
//        val desc: String,
//
//        @ColumnInfo(name = "poster")
//        val poster: String,
//
//        @ColumnInfo(name = "backdrop")
//        val backdrop: String,
//
//        @ColumnInfo(name = "date")
//        val date: String,
//
//        @ColumnInfo(name = "popularity")
//        val popularity: Double,
//
//        @ColumnInfo(name = "rating")
//        val rating: Double,
//
//        @ColumnInfo(name = "season")
//        val season: List<Season>?,
//
//        @ColumnInfo(name = "favorited")
//        var favorited: Boolean = false,
//
//        ) : Parcelable
