package nejati.me.omdbapi.webServices.omdpiModel.search.response.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Search {
    @SerializedName("Title")
    @Expose
    var title: String? = null
    @SerializedName("Year")
    @Expose
    var year: String? = null
    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = null
    @SerializedName("Type")
    @Expose
    var type: String? = null
    @SerializedName("Poster")
    @Expose
    var poster: String? = null

}