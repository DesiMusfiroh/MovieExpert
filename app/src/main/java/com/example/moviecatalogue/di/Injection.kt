package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogueRepository.getInstance(remoteDataSource)
    }
}