package nejati.me.sample.view.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import nejati.me.omdbapi.viewModels.detailActivity.DetailViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.Rating

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
object DetailBindingAdapters {

    /**
     * Bind Rating Adapter In Detail Movie Activity
     *
     * @param recyclerView
     * @param ratingItems
     * @param mainViewModel
     */
    @JvmStatic
    @BindingAdapter("bind:ratingItems", "bind:viewModel")
    fun ratingAdapter(
        recyclerView: RecyclerView,
        ratingItems: MutableList<Rating>,
        mainViewModel: DetailViewModel
    ) {
        if (recyclerView.adapter == null) {
            val adapter = RatingsAdapter(ratingItems, mainViewModel)
            recyclerView.adapter = adapter
        } else
            recyclerView.adapter?.notifyDataSetChanged()
    }
}
