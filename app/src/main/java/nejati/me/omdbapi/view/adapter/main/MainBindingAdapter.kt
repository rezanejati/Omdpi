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
object MainBindingAdapter {




    @JvmStatic
    @BindingAdapter("bind:fragmentManager","bind:fragments","bind:currentItem")
    fun bindMainViewPagerAdapter(viewPager: ViewPager,fragmentManager: FragmentManager,
                                 fragments: MutableList<FragmentModel>,currentItem:Int) {
        if (viewPager.adapter == null) {
            val  mainPagerAdapter =
                MainPagerAdapter(
                    fragmentManager,
                    FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments
                )

            viewPager.adapter = mainPagerAdapter
        }else{
          viewPager.adapter!!.notifyDataSetChanged()
        }
        viewPager.currentItem=currentItem

    }


    @JvmStatic
    @BindingAdapter("bind:pager")
     fun bindViewPagerTabs(view: TabLayout, pagerView: ViewPager?) {
        view.setupWithViewPager(pagerView, true)
    }


}
