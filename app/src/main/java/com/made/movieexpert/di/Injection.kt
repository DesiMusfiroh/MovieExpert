package com.made.movieexpert.di

import android.content.Context
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.data.source.local.LocalDataSource
import com.made.movieexpert.data.source.local.room.CatalogueDatabase
import com.made.movieexpert.data.source.remote.RemoteDataSource
import com.made.movieexpert.domain.repository.ICatalogueRepository
import com.made.movieexpert.domain.usecase.CatalogueInteractor
import com.made.movieexpert.domain.usecase.CatalogueUseCase
import com.made.movieexpert.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): ICatalogueRepository {
        val database = CatalogueDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()
        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
    fun provideTourismUseCase(context: Context): CatalogueUseCase {
        val repository = provideRepository(context)
        return CatalogueInteractor(repository)
    }
}