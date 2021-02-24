package com.kodingan.endemic.core.data.source.remote.network

import com.kodingan.endemic.core.data.source.remote.response.ListSpeciesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListSpeciesResponse
}
