package nejati.me.omdbapi.webServices.omdpiModel.search.response.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rating {
    @SerializedName("Source")
    @Expose
    var source: String? = null
    @SerializedName("Value")
    @Expose
    var value: String? = null

}