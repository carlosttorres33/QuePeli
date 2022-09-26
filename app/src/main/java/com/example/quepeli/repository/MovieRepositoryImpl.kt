package com.example.quepeli.repository

import com.example.quepeli.core.InternetCheck
import com.example.quepeli.data.local.LocalMovieDatSource
import com.example.quepeli.data.model.MovieList
import com.example.quepeli.data.model.toMovieEntity
import com.example.quepeli.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDatSource
) : MovieRepository {

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach {
                dataSourceLocal.saveMovie(it.toMovieEntity("toprated"))
            }

            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }

    }

    override suspend fun getNowPlayingMovies(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getNowPlayingMovies().results.forEach {
                dataSourceLocal.saveMovie(it.toMovieEntity("nowplaying"))
            }

            dataSourceLocal.getNowPlayingMovies()
        } else {
            dataSourceLocal.getNowPlayingMovies()
        }

    }

}