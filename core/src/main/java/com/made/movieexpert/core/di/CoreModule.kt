package com.made.movieexpert.di

import androidx.room.Room
import com.made.movieexpert.core.data.source.CatalogueRepository
import com.made.movieexpert.core.data.source.local.LocalDataSource
import com.made.movieexpert.core.data.source.local.room.CatalogueDatabase
import com.made.movieexpert.core.data.source.remote.RemoteDataSource
import com.made.movieexpert.core.data.source.remote.network.ApiService
import com.made.movieexpert.core.domain.repository.ICatalogueRepository
import com.made.movieexpert.core.utils.AppExecutors
import com.made.movieexpert.core.utils.Constants.BASE_URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<CatalogueDatabase>().catalogueDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("made".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CatalogueDatabase::class.java, "MovieExpert.db"
        ).fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICatalogueRepository> { CatalogueRepository(get(), get(), get()) }
}