package com.example.quepeli.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quepeli.core.BaseConcatHolder
import com.example.quepeli.databinding.FragmentMovieDetailBinding
import com.example.quepeli.databinding.NowPlayingRowBinding
import com.example.quepeli.databinding.TopRatedMoviesRowBinding
import com.example.quepeli.ui.movie.adapters.MovieAdapter

class NowPlayingAdapter (private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>(){

    private lateinit var binding : FragmentMovieDetailBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = NowPlayingRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    private inner class ConcatViewHolder(val binding: NowPlayingRowBinding): BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvNowPlayingMovies.adapter = adapter
        }
    }

}