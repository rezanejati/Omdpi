package nejati.me.sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import nejati.me.omdbapi.base.BaseAdapter
import nejati.me.omdbapi.base.BaseViewHolder
import nejati.me.omdbapi.databinding.RatingsListItemBinding
import nejati.me.omdbapi.viewModels.detail.DetailViewModel
import nejati.me.omdbapi.viewModels.detail.RatingsItemViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.Rating


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class RatingsAdapter(
    private val searchItems: MutableList<Rating>,
    detailViewModel: DetailViewModel
): BaseAdapter<BaseViewHolder, Rating>() {

    var detailViewModel: DetailViewModel

    init {
       this.detailViewModel=detailViewModel
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        val adapterBinding = RatingsListItemBinding.inflate(
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

    inner class ComicsListViewHolder(private val adapterBinding: RatingsListItemBinding) :
        BaseViewHolder(adapterBinding.root) {
        private var moviesItemViewModel: RatingsItemViewModel? = null


        override fun onBind(position: Int) {
            if (searchItems.size > 0) {
                val moviesListItem = searchItems[position]
                moviesItemViewModel =
                    RatingsItemViewModel(
                        moviesListItem
                    )
                adapterBinding.viewModel = moviesItemViewModel
                setFadeAnimation(adapterBinding.root)

            }
        }

    }


}
