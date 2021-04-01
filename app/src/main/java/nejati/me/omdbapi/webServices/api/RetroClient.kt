package nejati.me.omdbapi.webServices.api

import io.reactivex.Single
import nejati.me.omdbapi.webServices.helper.Const
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.DetailMovieResponse
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.OmdbpiSearchrResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
interface RetroClient {
    /**
     *
     * @param s Search Value
     * @param apikey OmdbApi Key
     * @param type Series Or Movies
     * @param page pagination
     * @return
     */
    @GET(Const.BASEURl)
    fun getMoviesList(
        @Query("s") s: String?,
        @Query("apikey") apikey: String?,
        @Query("type") type: String?,
        @Query("page") page: Int?
    ): Single<OmdbpiSearchrResponse>

    /**
     *
     * @param apikey OmdbApi Key
     * @param i Imdb Movie Id
     * @return
     */
    @GET(Const.BASEURl)
    fun getSearchById(
        @Query("apikey") apikey: String?,
        @Query("i") i: String?
    ): Single<DetailMovieResponse>
}
