package nejati.me.omdbapi.webServices.omdpiModel.search.response.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class DetailMovieResponse {
    @SerializedName("Title")
    @Expose
    var title: String? = null
    @SerializedName("Year")
    @Expose
    var year: String? = null
    @SerializedName("Rated")
    @Expose
    var rated: String? = null
    @SerializedName("Released")
    @Expose
    var released: String? = null
    @SerializedName("Runtime")
    @Expose
    var runtime: String? = null
    @SerializedName("Genre")
    @Expose
    var genre: String? = null
    @SerializedName("Director")
    @Expose
    var director: String? = null
    @SerializedName("Writer")
    @Expose
    var writer: String? = null
    @SerializedName("Actors")
    @Expose
    var actors: String? = null
    @SerializedName("Plot")
    @Expose
    var plot: String? = null
    @SerializedName("Language")
    @Expose
    var language: String? = null
    @SerializedName("Country")
    @Expose
    var country: String? = null
    @SerializedName("Awards")
    @Expose
    var awards: String? = null
    @SerializedName("Poster")
    @Expose
    var poster: String? = null
    @SerializedName("Ratings")
    @Expose
    var ratings: List<Rating>? = null
    @SerializedName("Metascore")
    @Expose
    var metascore: String? = null
    @SerializedName("imdbRating")
    @Expose
    var imdbRating: String? = null
    @SerializedName("imdbVotes")
    @Expose
    var imdbVotes: String? = null
    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = null
    @SerializedName("Type")
    @Expose
    var type: String? = null
    @SerializedName("DVD")
    @Expose
    var dVD: String? = null
    @SerializedName("BoxOffice")
    @Expose
    var boxOffice: String? = null
    @SerializedName("Production")
    @Expose
    var production: String? = null
    @SerializedName("Website")
    @Expose
    var website: String? = null
    @SerializedName("Response")
    @Expose
    var response: String? = null
    @SerializedName("Error")
    @Expose
    var error: String? = null
}