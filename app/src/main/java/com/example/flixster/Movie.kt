package com.example.flixster

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie (
    val movieId: Int,
    val voteAverage: Double,
    private val posterPath: String,
    val title: String,
    val overview: String,
    val popularity: String,
    val releaseDate: String,

): Parcelable{
    @IgnoredOnParcel
    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object{
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            //iterate through all movies in the json array and add an instance of each
            //Movie with only the selected fields from the json object
            for(i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        //extract specific fields you want from a json object
                        movieJson.getInt("id"),
                        movieJson.getDouble("vote_average"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getString("popularity"),
                        movieJson.getString("release_date")
                    )
                )
            }
            return movies
        }
    }
}