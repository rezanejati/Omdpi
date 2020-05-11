package nejati.me.omdbapi.viewModels.detail

import androidx.databinding.ObservableField
import nejati.me.omdbapi.view.adapter.CustomClickListener
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.Rating
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class RatingsItemViewModel(
    ratings: Rating
) {
    var ratingItems = ObservableField<Rating>()

    init {
        ratingItems.set(ratings)
    }


}
