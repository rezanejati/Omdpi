package nejati.me.omdbapi.view.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseFragment
import nejati.me.omdbapi.databinding.FragmentResultSearchBinding
import nejati.me.omdbapi.view.activities.detail.DetailMovieActivity
import nejati.me.omdbapi.viewModels.movieFragment.MovieViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class SearchResultFragment() :
    BaseFragment<FragmentResultSearchBinding, MovieViewModel>(),
    MovieNavigator {

    companion object {
        @JvmStatic
        fun newInstance(): SearchResultFragment {
            val args = Bundle()
            val fragment = SearchResultFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    /**
     * Set Variable fot DataBinding
     * @return
     */
    override val bindingVariable: Int
        get() = BR.viewModel

    /**
     * Set Layout For Fragment
     * @return
     */
    override val layoutRes: Int
        get() = R.layout.fragment_result_search

    override fun getViewModel(): Class<MovieViewModel> {
        return MovieViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.navigator = this

    }

    /**
     * On Movie Click
     * @param search
     */
    override fun onStartDetailMovieActivity(search: Search?) {
        startActivity(
            Intent(activity, DetailMovieActivity::class.java)
                .putExtra("ImdbApi", search!!.imdbID)
                .putExtra("title", search.title)
        )
    }


    /**
     * Create OmdbApi Search Request
     * @param searchType series or movie
     * @param searchValue search text
     */
    fun searchOmdbApi(searchType: String, searchValue: String) {
        viewModel!!.searchOmdbApi(searchType, searchValue)
    }

}

