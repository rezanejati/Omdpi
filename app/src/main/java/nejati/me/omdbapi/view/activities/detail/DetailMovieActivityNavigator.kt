package nejati.me.omdbapi.view.activities.detail

import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
 * Copyright © 2020
 */
interface DetailMovieActivityNavigator {
    fun onWebServiceError()
    fun onWebServiceMessage(message :String)
}