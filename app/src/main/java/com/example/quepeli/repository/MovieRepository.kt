package com.example.quepeli.repository

import com.example.quepeli.data.model.MovieList

interface MovieRepository {

    suspend fun getTopRatedMovies():MovieList
    suspend fun getNowPlayingMovies():MovieList

}