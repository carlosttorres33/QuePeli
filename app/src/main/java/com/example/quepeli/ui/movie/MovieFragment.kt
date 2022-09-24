package com.example.quepeli.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.quepeli.R
import com.example.quepeli.core.Resource
import com.example.quepeli.data.model.Movie
import com.example.quepeli.data.remote.MovieDataSource
import com.example.quepeli.databinding.FragmentMovieBinding
import com.example.quepeli.presentation.MovieViewModel
import com.example.quepeli.presentation.MovieViewModelFactory
import com.example.quepeli.repository.MovieRepositoryImpl
import com.example.quepeli.repository.RetrofitClient
import com.example.quepeli.ui.movie.adapters.MovieAdapter
import com.example.quepeli.ui.movie.adapters.concat.NowPlayingAdapter
import com.example.quepeli.ui.movie.adapters.concat.TopRatedConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding : FragmentMovieBinding

    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
        MovieDataSource(RetrofitClient.webservice)
    )) }

    private lateinit var concatAdapter : ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding=FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer{ result ->
            when(result){
                is Resource.Loading -> {
                    binding.rlProgressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.rlProgressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, TopRatedConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment) ))
                        addAdapter(1, NowPlayingAdapter(MovieAdapter(result.data.second.results, this@MovieFragment) ))

                    }

                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.rlProgressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }
        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movie.poster_path, movie.backdrop_path,
            movie.vote_average.toFloat(), movie.vote_count, movie.overview, movie.title, movie.original_language, movie.release_date)
        findNavController().navigate(action)
    }

}