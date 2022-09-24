package com.example.quepeli.repository

import com.example.quepeli.application.AppConstants
import com.example.quepeli.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey:String): MovieList

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey:String): MovieList

}

object RetrofitClient{
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebServices::class.java)
    }
}