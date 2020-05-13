package nejati.me.sample.di.api


import io.reactivex.Single
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.webServices.api.RetroClient
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.DetailMovieResponse
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.OmdbpiSearchrResponse
import javax.inject.Inject


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class OmdpApi
@Inject
constructor(private val api: RetroClient) {

    /**
     *
     * @param request Resuest For Get Search Movie
     * @return
     */
    fun getMovies(request: OmdpiRequestModel): Single<OmdbpiSearchrResponse> {
        return api.getMoviesList(
            request.searchName!!,
            request.apikey!!,
            request.type!!,
            request.page!!
        )
    }

    /**
     *
     * @param request Resuest For Get Detail Movie
     * @return
     */
    fun getSearchByID(request: OmdpiRequestModel): Single<DetailMovieResponse> {
        return api.getSearchById(request.apikey!!, request.imdbId!!)
    }


}
