package nejati.me.sample.view.adapter

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import nejati.me.omdbapi.model.FragmentModel
import nejati.me.omdbapi.view.adapter.mainActivity.MainPagerAdapter


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
object MainBindingAdapter {




    @JvmStatic
    @BindingAdapter("bind:fragmentManager","bind:fragments","bind:currentItem")
    fun bindMainViewPagerAdapter(viewPager: ViewPager, fragmentManager: FragmentManager,
                                 fragments: MutableList<FragmentModel>, currentItem:Int) {
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
