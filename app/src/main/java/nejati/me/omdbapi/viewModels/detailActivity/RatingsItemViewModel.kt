package nejati.me.omdbapi.viewModels.detailActivity

import androidx.databinding.ObservableField
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.Rating

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class RatingsItemViewModel(
    ratings: Rating
) {
    var ratingItems = ObservableField<Rating>()

    init {
        ratingItems.set(ratings)
    }


}
