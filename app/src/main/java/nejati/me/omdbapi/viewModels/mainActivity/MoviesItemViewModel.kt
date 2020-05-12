package nejati.me.omdbapi.viewModels.mainActivity

import androidx.databinding.ObservableField
import nejati.me.omdbapi.view.adapter.movieFragment.CustomClickListener
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class MoviesItemViewModel(
    result: Search?,
    private val customClickListener: CustomClickListener
) {
    var moviesItems = ObservableField<Search>()

    init {
        moviesItems.set(result)
    }

    fun onMovieClick(t: MoviesItemViewModel) {
        customClickListener.itemClicked(t)
    }

}
