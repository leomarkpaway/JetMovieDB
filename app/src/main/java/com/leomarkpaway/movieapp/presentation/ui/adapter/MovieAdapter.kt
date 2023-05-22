package com.leomarkpaway.movieapp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.leomarkpaway.movieapp.R
import com.leomarkpaway.movieapp.data.source.local.entity.Movie
import com.leomarkpaway.movieapp.databinding.ItemMovieBinding

class MovieAdapter(
    private val movies: List<Movie>,
    private val onClickItem: (Long?) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    lateinit var context: Context

    inner class MovieHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) = with(binding) {
            tvTitle.text = item.title
            tvShortDetail.text =
                context.getString(R.string.short_movie_detail, item.duration, item.genre)
            root.setOnClickListener { onClickItem(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        context = parent.context
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
    }

    override fun getItemCount() = movies.size

}