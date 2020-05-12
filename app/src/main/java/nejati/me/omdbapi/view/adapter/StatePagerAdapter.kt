package nejati.me.omdbapi.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import nejati.me.omdbapi.view.FragmentModel
import java.util.*

/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i></reza.n.j.t.i>@gmail.com>
 * Copyright © 2017
 */
class StatePagerAdapter(
    fm: FragmentManager,
    behavior: Int,
    private val mFragmentList: MutableList<FragmentModel>
) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentList[position].title
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

}