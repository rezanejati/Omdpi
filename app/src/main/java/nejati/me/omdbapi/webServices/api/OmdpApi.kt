package nejati.me.sample.di.api


import javax.inject.Inject

import io.reactivex.Single
import nejati.me.omdbapi.webServices.api.RetroClient
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.view.activities.detail.DetailMovieActivity
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.DetailMovieResponse
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.OmdbpiSearchrResponse


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class OmdpApi
@Inject
constructor(private val api: RetroClient) {

    fun getMovies(request: OmdpiRequestModel): Single<OmdbpiSearchrResponse> {
        return api.getMoviesList(request.searchName!!, request.apikey!!, request.type!!, request.page!!)
    }
    fun getSearchByID(request: OmdpiRequestModel): Single<DetailMovieResponse> {
        return api.getSearchById(request.apikey!!, request.imdbId!!)
    }


}
