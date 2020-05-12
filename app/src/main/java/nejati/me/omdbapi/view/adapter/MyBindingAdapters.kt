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
object MyBindingAdapters {

    @JvmStatic
    @BindingAdapter("moviesImage")
    fun loadImage(view: ImageView, imageUrl: String) {
        if(TextUtils.isEmpty(imageUrl)) return
        val options: RequestOptions = RequestOptions()

            .centerInside()
            .error(R.drawable.poster_not_found)
        Glide.with(view.context).load(imageUrl).apply(options).into(view)
    }

    @JvmStatic
    @BindingAdapter("blurelImage")
    fun loadDetailImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context).load(imageUrl).apply(RequestOptions.bitmapTransform(
            BlurTransformation(15, 3)
        )).into(view)

    }

}
