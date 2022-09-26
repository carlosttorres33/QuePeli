package com.example.quepeli.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.quepeli.core.Resource
import com.example.quepeli.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieViewModel(private val repo: MovieRepository):ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Success( Pair( repo.getTopRatedMovies(), repo.getNowPlayingMovies() ) ) )  //Se crea un data class en caso de querer devolver mas de 3 objetos
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }

}

class MovieViewModelFactory(private val repo: MovieRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}