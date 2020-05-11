package nejati.me.omdbapi.view.activities.mian

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseActivity
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.databinding.ActivityMainBinding
import nejati.me.omdbapi.view.activities.detail.DetailMovieActivity
import nejati.me.omdbapi.view.adapter.StatePagerAdapter
import nejati.me.omdbapi.view.fragment.movie.MovieFragment
import nejati.me.omdbapi.viewModels.main.MainViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search
import java.util.*


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainActivityNavigator, SearchView.OnQueryTextListener {
    var searchView: SearchView? = null
    var statePagerAdapter: StatePagerAdapter? = null


    var needSearch: Boolean = false



    /**
     * Set Variable fot DataBinding
     *
     * @return
     */
    override val bindingVariable: Int
        get() = BR.viewModel

    /**
     * Set Layout For Activity
     *
     * @return
     */
    override val layoutRes: Int
        get() = R.layout.activity_main
    /**
     * Add View Model
     *
     * @return
     */
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel!!.navigator = this
        setSupportActionBar(toolbar)
        setTitle(getString(R.string.app_name))

        statePagerAdapter= StatePagerAdapter(supportFragmentManager,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        statePagerAdapter!!.addFrag("movie",MovieFragment.newInstance())
        statePagerAdapter!!.addFrag("series",MovieFragment.newInstance())
        vpMulti!!.adapter=statePagerAdapter
        tabLayout!!.setupWithViewPager(vpMulti)
    // viewModel!!.statePagerAdapter=statePagerAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = menu.findItem(R.id.action_search)
            .getActionView() as SearchView

        searchView!!.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )

        searchView!!.setMaxWidth(Integer.MAX_VALUE)

        searchView!!.setOnQueryTextListener(this)

        return true
    }


    override fun onQueryTextSubmit(query: String?): Boolean {

        if (query!!.length > 3) {
            if (vpMulti.currentItem==0) {
              (statePagerAdapter!!.getItem(0) as MovieFragment).searchOmdbApi("movie", query)
            } else {
             (statePagerAdapter!!.getItem(1) as MovieFragment).searchOmdbApi("series", query)

            }

        }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        needSearch = true


        return false
    }


}
