package nejati.me.omdbapi.view.fragment.movie

import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
 * Copyright © 2020
 */
interface SearchFragmentNavigator {
    fun onStartDetailMovieActivity(search: Search?)
    fun onWebServiceError()

}