package com.kodingan.endemic.core.di

import androidx.room.Room
import com.kodingan.endemic.core.data.SpeciesRepository
import com.kodingan.endemic.core.data.source.local.LocalDataSource
import com.kodingan.endemic.core.data.source.local.room.SpeciesDatabase
import com.kodingan.endemic.core.data.source.remote.RemoteDataSource
import com.kodingan.endemic.core.data.source.remote.network.ApiService


import com.kodingan.endemic.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<SpeciesDatabase>().speciesDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("kodingan".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            SpeciesDatabase::class.java, "Species.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "kodingan.herokuapp.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/Vuy2zjFSPqF5Hz18k88DpUViKGbABaF3vZx5Raghplc=")
            .add(hostname, "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws=")
            .add(hostname, "sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()

    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kodingan.herokuapp.com/")
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
    single<com.kodingan.endemic.core.domain.repository.SpeciesRepository> {
        SpeciesRepository(
            get(),
            get(),
            get()
        )
    }
}