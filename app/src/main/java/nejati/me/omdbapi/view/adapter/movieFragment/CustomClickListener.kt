package nejati.me.omdbapi.view.adapter.movieFragment

import nejati.me.omdbapi.viewModels.mainActivity.MoviesItemViewModel

interface CustomClickListener {
    fun itemClicked(t: MoviesItemViewModel?)
}