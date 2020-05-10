package nejati.me.omdbapi.view.activities.mian

import nejati.me.omdbapi.webServices.omdpiModel.search.response.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
 * Copyright © 2019
 */
interface MainActivityNavigator {
    fun onDetail(search: Search?)
}