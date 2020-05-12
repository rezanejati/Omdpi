package nejati.me.omdbapi.view.activities.mian

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseActivity
import nejati.me.omdbapi.databinding.ActivityMainBinding
import nejati.me.omdbapi.view.adapter.mainActivity.MainPagerAdapter
import nejati.me.omdbapi.view.fragment.movie.SearchResultFragment
import nejati.me.omdbapi.viewModels.mainActivity.MainViewModel


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainActivityNavigator, SearchView.OnQueryTextListener {

/*    @set : Inject
    internal var mainPagerAdapter: MainPagerAdapter? = null*/

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
        setTitle(getString(R.string.search_movie_or_series))

        viewModel!!.addFragmentsIntoViewPager()

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

        when {
            !TextUtils.isEmpty(viewModel!!.lastSearch.get()) -> {
                searchView!!.setQuery(viewModel!!.lastSearch.get(), true)
                searchView!!.setIconified(false)
                searchView!!.clearFocus()
            }
        }
        return true
    }


    override fun onQueryTextSubmit(query: String?): Boolean {

        when {
            query!!.length > 3 -> {

                viewModel!!.lastSearch.set(query)
                viewModel!!.lastPagerPosition.set(vpMulti.currentItem )

                when {
                    vpMulti.currentItem == 0 ->
                        ((vpMulti!!.adapter as MainPagerAdapter).getItem(0) as SearchResultFragment)
                            .searchOmdbApi("movie", query)
                    vpMulti.currentItem == 1 ->
                        ((vpMulti!!.adapter as MainPagerAdapter).getItem(1) as SearchResultFragment)
                            .searchOmdbApi("series", query)
                }
            }
            else -> showSnackBar(dataBinding!!.root, "please enter minimum 3 characters")

        }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        needSearch = true
        return false
    }
}
