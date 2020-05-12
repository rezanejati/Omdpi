package nejati.me.sample.view.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import nejati.me.omdbapi.utility.MyScrollListener
import nejati.me.omdbapi.viewModels.movieFragment.MovieViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
object SearchMovieBindingAdapters {

    /**
     * Bind Movie Adapter In Search Result Fragment
     *
     * @param recyclerView
     * @param items
     * @param mainViewModel
     * @param pageNumber
     */
    @JvmStatic
    @BindingAdapter("bind:movieItems", "bind:viewModel", "bind:pageNumber")
    fun movieAdapter(
        recyclerView: RecyclerView,
        items: MutableList<Search>,
        mainViewModel: MovieViewModel, pageNumber: Int
    ) {

        when {
            recyclerView.adapter == null -> {
                val adapter = SearchMoviesAdapter(items, mainViewModel)
                recyclerView.adapter = adapter
            }
            pageNumber == 1 -> {
                recyclerView.adapter!!.notifyDataSetChanged()
            }
            else -> recyclerView.adapter!!.notifyItemInserted(recyclerView.adapter!!.itemCount)
        }

        recyclerView.addOnScrollListener(object : MyScrollListener(recyclerView.context) {
            override fun onEnd() {
                mainViewModel.callOmdbApiNextPage()
            }
        })
    }


}
