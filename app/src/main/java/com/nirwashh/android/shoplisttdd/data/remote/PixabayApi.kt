package com.nirwashh.android.shoplisttdd.data.remote

import com.nirwashh.android.shoplisttdd.BuildConfig
import com.nirwashh.android.shoplisttdd.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>
}