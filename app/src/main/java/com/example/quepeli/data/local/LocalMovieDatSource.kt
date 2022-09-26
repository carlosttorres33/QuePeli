package com.example.quepeli.data.local

import com.example.quepeli.data.model.MovieEntity
import com.example.quepeli.data.model.MovieList
import com.example.quepeli.data.model.toMovieList

class LocalMovieDatSource(private val movieDao: MovieDao) {

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getNowPlayingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "nowplaying" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }

}