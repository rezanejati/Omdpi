package nejati.me.sample.di.api


import javax.inject.Inject

import io.reactivex.Single
import nejati.me.omdbapi.webServices.api.RetroClient
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.OmdbpiSearchrResponse


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class OmdpApi
@Inject
constructor(private val api: RetroClient) {

    fun getMovies(request: OmdpiRequestModel): Single<OmdbpiSearchrResponse> {
        return api.getMoviesList(request.searchName!!, request.apikey!!)
    }


}
