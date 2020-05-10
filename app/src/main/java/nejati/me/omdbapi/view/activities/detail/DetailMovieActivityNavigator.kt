package nejati.me.omdbapi.view.activities.detail

import nejati.me.omdbapi.webServices.omdpiModel.search.response.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
 * Copyright © 2019
 */
interface DetailMovieActivityNavigator {
    fun onDetail(search: Search?)
}