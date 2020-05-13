package nejati.me.omdbapi.view.activities.detail

/**
 * Authors:
 * Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
 * Copyright © 2020
 */
interface DetailMovieActivityNavigator {

    fun onWebServiceError()
    fun onWebServiceMessage(message: String)
    fun onNetworkStatus(isConnectedToInternet: Boolean)
}