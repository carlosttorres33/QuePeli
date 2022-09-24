package com.example.quepeli.repository

import com.example.quepeli.data.model.MovieList
import com.example.quepeli.data.remote.MovieDataSource

class MovieRepositoryImpl( private val dataSource : MovieDataSource):MovieRepository {

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getNowPlayingMovies(): MovieList = dataSource.getNowPlayingMovies()
}