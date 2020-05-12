package nejati.me.omdbapi.webServices.omdpiModel.search.response.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OmdbpiSearchrResponse {
    @SerializedName("Search")
    @Expose
    var search: List<Search>? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null
    @SerializedName("Response")
    @Expose
    var response: String? = null
    @SerializedName("Error")
    @Expose
    var error: String? = null

}