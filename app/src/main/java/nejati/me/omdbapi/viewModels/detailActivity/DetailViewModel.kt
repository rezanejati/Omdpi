package nejati.me.omdbapi.viewModels.detailActivity

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import nejati.me.omdbapi.BuildConfig
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.base.ActivityBaseViewModel
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.view.activities.detail.DetailMovieActivityNavigator
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.DetailMovieResponse
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.Rating
import nejati.me.sample.di.api.OmdpApi
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class DetailViewModel() : ActivityBaseViewModel<DetailMovieActivityNavigator>() {
    private var disposable: CompositeDisposable? = null
    private var api: OmdpApi? = null
    private var rxSingleSchedulers: RxSingleSchedulers? = null
    private var requestModel: OmdpiRequestModel? = null


    init {
        disposable = CompositeDisposable()
    }

    @Inject
    constructor(api: OmdpApi, rxSingleSchedulers: RxSingleSchedulers) : this() {
        this.api = api
        this.rxSingleSchedulers = rxSingleSchedulers
    }

    /**
     * get data from web service
     */
    fun getData() {
        showProgressLayout.set(true)
        api?.getSearchByID(requestModel)
            ?.compose(rxSingleSchedulers?.applySchedulers())
            ?.subscribe({ onReady(it) }, { onError() })?.let { disposable?.add(it) }
    }


    /**
     * The web service Error
     * @param message
     */
    fun onError() {
        showProgressLayout.set(false)
        navigator?.onWebServiceError()

    }

    var detailMovieResponse = ObservableField<DetailMovieResponse>()
    var ratingObservable = ObservableArrayList<Rating>()

    /**
     * OmdbApi Response
     * @param result Response Of OmdbApiResponse Api
     */
    fun onReady(result: DetailMovieResponse) {
        showProgressLayout.set(false)

        if (result.response?.toBoolean() == true) {
            detailMovieResponse.set(result)
            result.ratings?.let { ratingObservable.addAll(it) }

        } else {
            result.error?.let { navigator?.onWebServiceMessage(it) }

        }

    }


    /**
     *
     * @param imdbId Movie ID For Get Detail From Web Service
     */
    fun getDetailMovie(imdbId: String) {
        requestModel = OmdpiRequestModel()
        requestModel?.apikey = BuildConfig.API_KEY
        requestModel?.imdbId = imdbId
        getData()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun clearDisposable() {
        disposable?.clear()


    }

    override fun isInternetAvailable(isConnectedToInternet: Boolean) {
        navigator?.onNetworkStatus(isConnectedToInternet)

    }
}

