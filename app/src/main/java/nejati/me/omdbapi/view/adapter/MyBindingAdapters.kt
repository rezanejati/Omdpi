package nejati.me.sample.view.adapter

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseActivity
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.view.activities.mian.MainActivity
import nejati.me.omdbapi.view.adapter.StatePagerAdapter
import nejati.me.omdbapi.view.fragment.movie.MovieFragment
import nejati.me.omdbapi.viewModels.movie.MovieViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search
import java.util.ArrayList


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
object MyBindingAdapters {

    @JvmStatic
    @BindingAdapter("bind:recyclerAdapter", "bind:itemClick", "bind:pageNumber")
    fun addSearchItems(
        recyclerView: RecyclerView,
        items: MutableList<Search>,
        mainViewModel: MovieViewModel, pageNumber: Int
    ) {

        if (recyclerView.adapter == null) {
            val adapter = SearchMoviesAdapter(items, mainViewModel)
            recyclerView.adapter = adapter
        } else if (pageNumber == 1) {
            // recyclerView.removeAllViews()
            recyclerView.adapter!!.notifyDataSetChanged()
        } else {
            recyclerView.adapter!!.notifyItemInserted(recyclerView.adapter!!.itemCount)
        }
    }

    /*   @JvmStatic
        @BindingAdapter("bind:viewPagerFargments","bind:fragmentTitle","bind:fragmentManager")
        fun bindViewPagerAdapter(viewPager: ViewPager,viewPagerFargments: MutableList<Fragment>,
                                 title :MutableList<String>
                                 ,fragmentManager:FragmentManager) {
            if (viewPager.adapter == null) {

                val statePagerAdapter = StatePagerAdapter(fragmentManager,
                    FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,viewPagerFargments,title)
                viewPager.setAdapter(statePagerAdapter)
            }


        }*/
    @JvmStatic
    @BindingAdapter("bind:adapter")
    fun addFragmentInViewPager(viewPager: ViewPager, adapter: StatePagerAdapter) {

        if (viewPager.adapter == null) {
            viewPager.adapter = adapter


        }
    }

    @JvmStatic
    @BindingAdapter("moviesImage")
    fun loadImage(view: ImageView, imageUrl: String) {
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


    @JvmStatic
    @BindingAdapter("startScaleUpAnimation")
    fun setAnimation(view: View, start: Boolean) {
        val animation = view.getAnimation()
        if (start && animation == null) {
            view.startAnimation(
                AnimationUtils.loadAnimation(
                    BaseApplication.get(),
                    R.anim.scale_up
                )
            )
        } else if (animation != null) {
            animation.cancel()
            view.setAnimation(null)
        }
    }


    /*  @JvmStatic
      @BindingAdapter("bind:adapter")
      fun addFragmentInViewPager(viewPager: ViewPager, adapter: HomePagerAdapter) {

          if (viewPager.adapter == null) {
              viewPager.adapter = adapter


          }
      }*/
}
