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

    /**
     *
     *
     * @param viewPager Fragment Of Search Type
     * @param fragmentManager Get Fragment Manager From Base Activity
     * @param fragments View Pager Fragments Seris Or Movie ...
     * @param currentItem Set Current View Pager
     * @param onPageChange On Page Change Listener
     */
    @JvmStatic
    @BindingAdapter(
        "bind:fragmentManager",
        "bind:fragments",
        "bind:currentItem",
        "bind:onPageChange"
    )
    fun bindMainViewPagerAdapter(
        viewPager: ViewPager,
        fragmentManager: FragmentManager,
        fragments: MutableList<FragmentModel>,
        currentItem: Int,
        listener: OnMainActivityViewPagerEvent?
    ) {
        when {
            viewPager.adapter == null -> {
                val mainPagerAdapter =
                    MainPagerAdapter(
                        fragmentManager,
                        FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments
                    )
                viewPager.adapter = mainPagerAdapter
                viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                    override fun onPageSelected(position: Int) {
                        listener?.onPageChanged(position)
                    }
                })
            }
            else -> viewPager.adapter?.notifyDataSetChanged()
        }
        viewPager.currentItem = currentItem
    }

    /**
     *
     * @param view Tablayout
     * @param pagerView Main Activity Search View Pager
     */
    @JvmStatic
    @BindingAdapter("bind:pager")
    fun bindViewPagerTabs(view: TabLayout, pagerView: ViewPager?) {
        view.setupWithViewPager(pagerView, true)
    }

    /**
     * Listener ViewPager In MainActivity
     *
     */
    interface OnMainActivityViewPagerEvent {
        fun onPageChanged(position: Int)
    }
}
