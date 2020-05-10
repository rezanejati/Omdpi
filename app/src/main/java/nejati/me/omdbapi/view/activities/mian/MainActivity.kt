package nejati.me.omdbapi.view.activities.mian

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseActivity
import nejati.me.omdbapi.databinding.ActivityMainBinding
import nejati.me.omdbapi.viewModels.main.MainViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.Search
import kotlinx.android.synthetic.main.activity_main.*
import nejati.me.omdbapi.BuildConfig
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */

class MainActivity :  BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainActivityNavigator, SearchView.OnQueryTextListener {
    var searchView: SearchView? = null


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
    }
    fun createSearchRequest(title:String): OmdpiRequestModel {
        val OmdpiRequestModel = OmdpiRequestModel()

        if(viewModel!!.isMovies.get()!!){
            OmdpiRequestModel.type = "movie"

        }else{
            OmdpiRequestModel.type = "series"

        }
        OmdpiRequestModel.searchName = title
        OmdpiRequestModel.apikey = BuildConfig.API_KEY
        return OmdpiRequestModel
    }

    override fun onDetail(search: Search?) {

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
            viewModel!!.requestModel = createSearchRequest(query)
            viewModel!!.callOmdbApi()
        }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {

        return false
    }


}
