package nejati.me.omdbapi.view.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseFragment
import nejati.me.omdbapi.databinding.FragmentSearchBinding
import nejati.me.omdbapi.view.activities.detail.DetailMovieActivity
import nejati.me.omdbapi.viewModels.movieFragment.MovieViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class SearchFragment() :
    BaseFragment<FragmentSearchBinding, MovieViewModel>(),
    SearchFragmentNavigator {

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_IMDB_ID = "ImdbID"

        @JvmStatic
        fun newInstance(): SearchFragment {
            val args = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * Set Variable for DataBinding
     * @return
     */
    override val bindingVariable: Int
        get() = BR.viewModel

    /**
     * Set Layout For Fragment
     * @return
     */
    override val layoutRes: Int
        get() = R.layout.fragment_search

    override fun getViewModel(): Class<MovieViewModel> {
        return MovieViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.navigator = this
    }

    /**
     * On Movie Click
     * @param search
     */
    override fun onStartDetailMovieActivity(search: Search?) {
        startActivity(
            Intent(activity, DetailMovieActivity::class.java)
                .putExtra(EXTRA_IMDB_ID, search?.imdbID)
                .putExtra(EXTRA_TITLE, search?.title)
        )
    }

    override fun onWebServiceError() {
        viewModel?.errorMessage?.set(getString(R.string.webservice_not_available))
    }


    /**
     * Create OmdbApi Search Request
     * @param searchType series or movie
     * @param searchValue search text
     */
    fun searchOmdbApi(searchType: String, searchValue: String?) {
        searchValue?.let { viewModel?.searchOmdbApi(searchType, it) }
    }
}

