package com.unhas.simplemovieapp.view.tvseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unhas.simplemovieapp.R
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResultsItem
import com.unhas.simplemovieapp.databinding.ItemsFilmBinding

class TVSeriesAdapter: RecyclerView.Adapter<TVSeriesAdapter.TVSeriesViewHolder>() {

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var listTVSeries = ArrayList<TVSeriesResultsItem>()

    fun setTVSeries(tvSeries: List<TVSeriesResultsItem>?){
        if(tvSeries == null) return
        listTVSeries.clear()
        listTVSeries.addAll(tvSeries)
    }

    inner class TVSeriesViewHolder(private val binding: ItemsFilmBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvSeries: TVSeriesResultsItem){
            with(binding){
                textItemTitle.text = tvSeries.name
                textScore.text = itemView.context.getString(R.string.score, tvSeries.voteAverage)
                textSummary.text = tvSeries.overview

                Glide.with(itemView.context)
                    .load(IMAGE_URL + tvSeries.posterPath)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVSeriesViewHolder {
        val itemsMovieBinding = ItemsFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVSeriesViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: TVSeriesViewHolder, position: Int) {
        val movie = listTVSeries[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTVSeries.size
}