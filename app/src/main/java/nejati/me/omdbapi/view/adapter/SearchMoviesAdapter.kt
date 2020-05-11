package nejati.me.sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import nejati.me.omdbapi.base.BaseAdapter
import nejati.me.omdbapi.base.BaseViewHolder
import nejati.me.omdbapi.databinding.MovieListItemBinding
import nejati.me.omdbapi.view.adapter.CustomClickListener
import nejati.me.omdbapi.viewModels.main.MoviesItemViewModel
import nejati.me.omdbapi.viewModels.movie.MovieViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class SearchMoviesAdapter(
    private val searchItems: MutableList<Search>,
    mainViewModel: MovieViewModel
): BaseAdapter<BaseViewHolder, Search>() {

    var mainViewModel: MovieViewModel

    init {
       this.mainViewModel=mainViewModel
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        val adapterBinding = MovieListItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false)

        return ComicsListViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    inner class ComicsListViewHolder(private val adapterBinding: MovieListItemBinding) :
        BaseViewHolder(adapterBinding.root), CustomClickListener {
        private var moviesItemViewModel: MoviesItemViewModel? = null


        override fun onBind(position: Int) {
            if (searchItems.size > 0) {
                val moviesListItem = searchItems[position]
                moviesItemViewModel = MoviesItemViewModel(moviesListItem, this)
                adapterBinding.viewModel = moviesItemViewModel
                setFadeAnimation(adapterBinding.root)

            }
        }


        override fun itemClicked(f: MoviesItemViewModel) {
            mainViewModel.onMoviesItemClick(adapterPosition)
        }
    }


}
