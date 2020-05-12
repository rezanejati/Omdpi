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
import com.readystatesoftware.chuck.internal.ui.MainActivity
import jp.wasabeef.glide.transformations.BlurTransformation
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.view.FragmentModel
import nejati.me.omdbapi.view.adapter.StatePagerAdapter
import nejati.me.omdbapi.view.fragment.movie.MovieFragment
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
    @BindingAdapter("bind:fragmentManager","bind:fragments")
    fun bindViewPagerAdapter(viewPager: ViewPager, fragmentManager: FragmentManager, fragments: MutableList<FragmentModel>) {
        if (viewPager.adapter == null) {
            val statePagerAdapter: StatePagerAdapter?
            statePagerAdapter= StatePagerAdapter(fragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments)

            viewPager.adapter = statePagerAdapter

        }else{
            viewPager.adapter!!.notifyDataSetChanged()
        }

    }
    @JvmStatic
    @BindingAdapter("bind:pager")
     fun bindViewPagerTabs(view: TabLayout, pagerView: ViewPager?) {
        view.setupWithViewPager(pagerView, true)
    }


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
