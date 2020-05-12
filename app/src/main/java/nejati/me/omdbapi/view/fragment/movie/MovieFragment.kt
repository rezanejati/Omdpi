package nejati.me.omdbapi.view.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_movie.*
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.BuildConfig
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseFragment
import nejati.me.omdbapi.databinding.FragmentMovieBinding
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.utility.MyScrollListener
import nejati.me.omdbapi.view.activities.detail.DetailMovieActivity
import nejati.me.omdbapi.viewModels.movie.MovieViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search


/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i></reza.n.j.t.i>@gmail.com>
 * Copyright © 2017
 */
class MovieFragment() :
    BaseFragment<FragmentMovieBinding, MovieViewModel>(),
    MovieNavigator {




    companion object {
        @JvmStatic
        fun newInstance(): MovieFragment {
            val args = Bundle()
            val fragment = MovieFragment()
            fragment.setArguments(args)
            return fragment
        }

    }


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutRes: Int
        get() = R.layout.fragment_movie

    override fun getViewModel(): Class<MovieViewModel> {
        return MovieViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel!!.navigator = this

        activity?.let {
            rvMovies.addOnScrollListener(object : MyScrollListener(it) {

                override fun onEnd() {
                    viewModel!!.callComicsWebServiceForNextPage()

                }

                override fun onFirst() {

                }

                override fun onMoved(distance: Int, dy: Int) {

                }
            })
        }


    }

    override fun onDetail(search: Search?) {
        startActivity(Intent(activity, DetailMovieActivity::class.java)
            .putExtra("ImdbApi",search!!.imdbID)
            .putExtra("title", search.title))
    }

    fun searchOmdbApi(searchType:String,searchValue:String){
        val OmdpiRequestModel = OmdpiRequestModel()
        OmdpiRequestModel.type = searchType
        OmdpiRequestModel.searchName = searchValue
        OmdpiRequestModel.apikey = BuildConfig.API_KEY
        OmdpiRequestModel.page = 1
        viewModel!!.requestModel=OmdpiRequestModel
        viewModel!!.callOmdbApi()
    }

}

