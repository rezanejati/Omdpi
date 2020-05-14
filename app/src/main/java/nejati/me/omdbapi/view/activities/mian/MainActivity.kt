package nejati.me.omdbapi.view.activities.mian

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseActivity
import nejati.me.omdbapi.databinding.ActivityMainBinding
import nejati.me.omdbapi.view.fragment.movie.SearchFragment
import nejati.me.omdbapi.viewModels.mainActivity.MainViewModel


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainActivityNavigator, SearchView.OnQueryTextListener {

    var searchView: SearchView? = null

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

        title = getString(R.string.search_movie_or_series)

        viewModel!!.addFragmentsIntoViewPager()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView

        searchView!!.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )

        searchView!!.maxWidth = Integer.MAX_VALUE

        searchView!!.setOnQueryTextListener(this)

        when {
            !TextUtils.isEmpty(viewModel!!.lastSearch.get()) -> {
                searchView!!.setQuery(viewModel!!.lastSearch.get(), true)
                searchView!!.isIconified = false
                searchView!!.clearFocus()
            }
        }
        return true
    }


    override fun onQueryTextSubmit(query: String?): Boolean {

        when {

            query!!.length > 2 -> {

                viewModel!!.lastSearch.set(query)

                when {
                    viewModel!!.viewPagerPosition.get() == 0 ->
                        (viewModel!!.fragments[0].fragment as SearchFragment).searchOmdbApi(
                            "movie",
                            query
                        )

                    viewModel!!.viewPagerPosition.get() == 1 ->
                        (viewModel!!.fragments[1].fragment as SearchFragment).searchOmdbApi(
                            "series",
                            query
                        )
                }
            }
            else -> showSnackBar(dataBinding!!.root, getString(R.string.least_3_characters))

        }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        needSearch = true
        return false
    }

    override fun onNetworkStatus(isConnectedToInternet: Boolean) {
        when {
            !isConnectedToInternet -> showSnackBar(
                dataBinding!!.root,
                getString(R.string.network_not_available)
            )
        }
    }

}
