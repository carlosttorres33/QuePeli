package com.example.quepeli.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quepeli.core.BaseViewHolder
import com.example.quepeli.data.model.Movie
import com.example.quepeli.databinding.MovieItemBinding

class MovieAdapter(private val moviesList: List<Movie>, private val itemClickListener : OnMovieClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnMovieClickListener{
        fun onMovieClick(movie:Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(moviesList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(moviesList[position])

        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context):BaseViewHolder<Movie>(binding.root){
        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.idImageMovie)
            binding.tvTitle.text = item.title
            binding.tvTextRating.text = "${item.vote_average}"
        }
    }

}