package nejati.me.sample.view.adapter

import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import jp.wasabeef.glide.transformations.BlurTransformation
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.utility.MyScrollListener
import nejati.me.omdbapi.view.FragmentModel
import nejati.me.omdbapi.view.adapter.main.MainPagerAdapter
import nejati.me.omdbapi.viewModels.detail.DetailViewModel
import nejati.me.omdbapi.viewModels.movie.MovieViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.Rating
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
object DetailBindingAdapters {


    @JvmStatic
    @BindingAdapter("bind:ratingItems",  "bind:viewModel")
    fun ratingAdapter(
        recyclerView: RecyclerView,
        ratingItems: MutableList<Rating>,
        mainViewModel: DetailViewModel
    ) {
        if (recyclerView.adapter == null) {
            val adapter = RatingsAdapter(ratingItems, mainViewModel)
            recyclerView.adapter = adapter

        } else
            recyclerView.adapter!!.notifyDataSetChanged()
    }


    @JvmStatic
    @BindingAdapter("bind:movieItems", "bind:viewModel", "bind:pageNumber")
    fun movieAdapter(
        recyclerView: RecyclerView,
        items: MutableList<Search>,
        mainViewModel: MovieViewModel, pageNumber: Int
    ) {
        if (recyclerView.adapter == null) {
            val adapter = SearchMoviesAdapter(items, mainViewModel)
            recyclerView.adapter = adapter
        } else if (pageNumber == 1) {
            recyclerView.adapter!!.notifyDataSetChanged()
        } else {
            recyclerView.adapter!!.notifyItemInserted(recyclerView.adapter!!.itemCount)
        }
        recyclerView.addOnScrollListener(object : MyScrollListener(recyclerView.context) {
            override fun onEnd() {
                mainViewModel.callComicsWebServiceForNextPage()
            }
        })
    }
    


}
