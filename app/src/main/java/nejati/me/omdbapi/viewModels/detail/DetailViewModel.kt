package nejati.me.omdbapi.viewModels.detail

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.base.ActivityBaseViewModel
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.view.activities.detail.DetailMovieActivityNavigator
import nejati.me.omdbapi.view.activities.mian.MainActivityNavigator
import nejati.me.omdbapi.webServices.omdpiModel.search.response.OmdbpiSearchrResponse
import nejati.me.omdbapi.webServices.omdpiModel.search.response.Search
import nejati.me.sample.di.api.OmdpApi
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class DetailViewModel() : ActivityBaseViewModel<DetailMovieActivityNavigator>() {
    private var disposable: CompositeDisposable? = null
    private var api: OmdpApi? = null
    private var rxSingleSchedulers: RxSingleSchedulers? = null
    public var requestModel : OmdpiRequestModel ? = null

    init {
        disposable = CompositeDisposable()
    }



    @Inject
    constructor(api: OmdpApi, rxSingleSchedulers: RxSingleSchedulers) : this() {
        this.api=api
        this.rxSingleSchedulers=rxSingleSchedulers
    }

    /**
     * get data from web service
     */
    fun getData() {
        disposable!!.add(api!!.getMovies(requestModel!!)
            .compose(rxSingleSchedulers!!.applySchedulers())
            .subscribe({onReady(it)}, { onError()}))
    }


    /**
     * the web service error message
     * @param message
     */
    fun onError() {
    }

    /**
     * OmdbApi Response
     * @param result Response Of OmdbApiResponse Api
     */
    fun onReady(result: OmdbpiSearchrResponse) {


    }
    fun callOmdbApi() {

        getData()

    }
    fun setMovieList(list: List<Search>) {


    }

    fun onMoviesItemClick(position: Int) {

    }

}

