package nejati.me.omdbapi.webServices.api

import io.reactivex.Single
import nejati.me.omdbapi.webServices.helper.Const
import nejati.me.omdbapi.webServices.omdpiModel.search.response.OmdbpiSearchrResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
interface RetroClient {

    @GET(Const.BASEURl)
    fun getMoviesList(@Query("s") ts: String,
                      @Query("apikey") apikey: String,
                      @Query("type") type: String): Single<OmdbpiSearchrResponse>
}
