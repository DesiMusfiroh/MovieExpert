package com.made.movieexpert.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.made.movieexpert.core.data.source.local.entity.MovieEntity
import com.made.movieexpert.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 4, exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase(){

    abstract fun catalogueDao(): CatalogueDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogueDatabase? = null

        fun getInstance(context: Context): CatalogueDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CatalogueDatabase::class.java,
                    "MovieExpert.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}