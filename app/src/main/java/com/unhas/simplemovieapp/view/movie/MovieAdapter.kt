package com.unhas.simplemovieapp.view.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unhas.simplemovieapp.R
import com.unhas.simplemovieapp.data.source.remote.response.MovieResultsItem
import com.unhas.simplemovieapp.databinding.ItemsFilmBinding

class MovieAdapter constructor(private val context: Context): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var listMovies = ArrayList<MovieResultsItem>()

    fun setMovies(movies: List<MovieResultsItem>?){
        if(movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    inner class MovieViewHolder(private val binding: ItemsFilmBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResultsItem, context: Context){
            with(binding){
                textItemTitle.text = movie.title
                textScore.text = itemView.context.getString(R.string.score, movie.voteAverage)
                textSummary.text = movie.overview

                Glide.with(itemView.context)
                    .load(IMAGE_URL + movie.posterPath)
                    .into(imgPoster)

                btnShare.setOnClickListener {
                    val textMessage = "${movie.title}\n" +
                            "Rating: ${movie.voteAverage} / 10\n" +
                            "Summary: ${movie.overview}"

                    val sendIntent = Intent().apply{
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, textMessage)
                        type = "text/plain"
                    }

                    context.startActivity(sendIntent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie, context)
    }

    override fun getItemCount(): Int = listMovies.size
}