package com.example.moviecatalogue.di

import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): CatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogueRepository.getInstance(remoteDataSource)
    }
}