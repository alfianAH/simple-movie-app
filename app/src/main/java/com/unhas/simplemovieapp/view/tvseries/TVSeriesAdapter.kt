package com.unhas.simplemovieapp.view.tvseries

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unhas.simplemovieapp.R
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResultsItem
import com.unhas.simplemovieapp.databinding.ItemsFilmBinding

class TVSeriesAdapter constructor(private val context: Context): RecyclerView.Adapter<TVSeriesAdapter.TVSeriesViewHolder>() {

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

        fun bind(tvSeries: TVSeriesResultsItem, context: Context){
            with(binding){
                textItemTitle.text = tvSeries.name
                textScore.text = itemView.context.getString(R.string.score, tvSeries.voteAverage)
                textSummary.text = tvSeries.overview

                Glide.with(itemView.context)
                    .load(IMAGE_URL + tvSeries.posterPath)
                    .into(imgPoster)

                btnShare.setOnClickListener {
                    val textMessage = "${tvSeries.name}\n" +
                            "Rating: ${tvSeries.voteAverage} / 10\n" +
                            "Summary: ${tvSeries.overview}"

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVSeriesViewHolder {
        val itemsMovieBinding = ItemsFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVSeriesViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: TVSeriesViewHolder, position: Int) {
        val movie = listTVSeries[position]
        holder.bind(movie, context)
    }

    override fun getItemCount(): Int = listTVSeries.size
}