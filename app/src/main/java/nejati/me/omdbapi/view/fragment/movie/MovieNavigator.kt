package nejati.me.omdbapi.view.fragment.movie

import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
 * Copyright © 2019
 */
interface MovieNavigator {
    fun onDetail(search: Search?)

}