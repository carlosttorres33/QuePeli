package com.example.quepeli.data.remote

import com.example.quepeli.application.AppConstants
import com.example.quepeli.data.model.MovieList
import com.example.quepeli.repository.WebServices

class MovieDataSource(private val webServices: WebServices) {

    suspend fun getTopRatedMovies(): MovieList {
        return webServices.getTopRatedMovies(AppConstants.API_KEY)
    }

    suspend fun getNowPlayingMovies(): MovieList {
        return webServices.getNowPlayingMovies(AppConstants.API_KEY)
    }

}