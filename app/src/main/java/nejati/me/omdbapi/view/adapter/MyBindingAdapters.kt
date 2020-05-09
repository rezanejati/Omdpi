package nejati.me.sample.view.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.view.animation.AnimationUtils
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.viewModels.main.MainViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.Search


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
object MyBindingAdapters {

    @JvmStatic@BindingAdapter("bind:recyclerAdapter", "bind:itemClick")
    fun addSearchItems(
        recyclerView: RecyclerView,
        items: MutableList<Search>,
        mainViewModel: MainViewModel) {

        if (recyclerView.adapter == null) {
            val adapter = SearchMoviesAdapter(items, mainViewModel)
            recyclerView.adapter = adapter
        } else {
            recyclerView.adapter!!.notifyDataSetChanged()
        }
    }

    @JvmStatic@BindingAdapter("moviesImage")
    fun loadImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context).load(imageUrl).into(view)
    }
    @JvmStatic@BindingAdapter("detailImage")
    fun loadDetailImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context).load(imageUrl).into(view)
    }


    @JvmStatic@BindingAdapter("startScaleUpAnimation")
    fun setAnimation(view: View, start: Boolean) {
        val animation = view.getAnimation()
        if (start && animation == null) {
            view.startAnimation(AnimationUtils.loadAnimation(BaseApplication.get(), R.anim.scale_up))
        } else if (animation != null) {
            animation.cancel()
            view.setAnimation(null)
        }
    }
}
