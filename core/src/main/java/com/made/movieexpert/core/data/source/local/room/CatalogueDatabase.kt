package com.made.movieexpert.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.made.movieexpert.core.data.source.local.entity.MovieEntity
import com.made.movieexpert.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 4, exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase(){
    abstract fun catalogueDao(): CatalogueDao
}