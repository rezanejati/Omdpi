package nejati.me.omdbapi.view.fragment.series

import android.os.Bundle
import android.view.View
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.BuildConfig
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseFragment
import nejati.me.omdbapi.databinding.FragmentSeriesBinding
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.viewModels.series.SeriesViewModel
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search


/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i></reza.n.j.t.i>@gmail.com>
 * Copyright © 2017
 */
class SeriesFragment() :
    BaseFragment<FragmentSeriesBinding, SeriesViewModel>(),
    SeriesNavigator {




    companion object {
        @JvmStatic
        fun newInstance(): SeriesFragment {
            val args = Bundle()
            val fragment = SeriesFragment()
            fragment.setArguments(args)
            return fragment
        }

    }


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutRes: Int
        get() = R.layout.fragment_series

    override fun getViewModel(): Class<SeriesViewModel> {
        return SeriesViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel!!.navigator = this

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.requestModel=createSearchRequest("ins")
        viewModel!!.callOmdbApi()

    }

    override fun onDetail(search: Search?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun createSearchRequest(title:String): OmdpiRequestModel {
        val OmdpiRequestModel = OmdpiRequestModel()
        OmdpiRequestModel.type = "series"
        OmdpiRequestModel.searchName = title
        OmdpiRequestModel.apikey = BuildConfig.API_KEY
        OmdpiRequestModel.page = 1
        return OmdpiRequestModel
    }

}

