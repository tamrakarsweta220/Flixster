package com.example.flixster

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG="MovieAdapter"
class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    //expensive operation: create a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    //cheap operation: simply bind data to an existing viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie){
            tvTitle.text =  movie.title
            tvOverview.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }

        override fun onClick(v: View?) {
            //1. Get notified on the particular movie which was clicked
            val movie = movies[adapterPosition]
            //2. Use the intent system to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}
