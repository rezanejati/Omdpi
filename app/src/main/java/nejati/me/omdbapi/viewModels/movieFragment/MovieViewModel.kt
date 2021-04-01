package nejati.me.omdbapi.viewModels.movieFragment

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import nejati.me.omdbapi.BuildConfig
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.base.FragmentBaseViewModel
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.view.fragment.movie.SearchFragmentNavigator
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.OmdbpiSearchrResponse
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search
import nejati.me.sample.di.api.OmdpApi
import javax.inject.Inject


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class MovieViewModel() : FragmentBaseViewModel<SearchFragmentNavigator>() {

    private var disposable: CompositeDisposable? = null

    private var rxSingleSchedulers: RxSingleSchedulers? = null

    private var api: OmdpApi? = null

    private var requestModel: OmdpiRequestModel? = null

    var showPaginationProgress = ObservableField<Boolean>(false)

    val moviesResult = ObservableArrayList<Search>()

    var showWaitingSearchLayout = ObservableField(true)

    var showResultRecyclerView = ObservableField(false)

    var haveNextPage = ObservableField(true)

    var resultPage = ObservableField(1)

    init {
        disposable = CompositeDisposable()
    }

    /**
     * inject retro client
     */
    @Inject
    constructor(api: OmdpApi, rxSingleSchedulers: RxSingleSchedulers) : this() {
        this.api = api
        this.rxSingleSchedulers = rxSingleSchedulers
    }

    /**
     * get data from web service
     */

    fun getData() {
        api?.getMovies(requestModel)
            ?.compose(rxSingleSchedulers?.applySchedulers())
            ?.subscribe({ onReady(it) }, { onErrorRetroClient() })?.let { disposable?.add(it) }
    }


    fun onStartWebservice() {
        showProgressLayout.set(true)
        showWaitingSearchLayout.set(false)
        showErrorLayout.set(false)
    }

    fun onFinishWebService() {
        showProgressLayout.set(false)
        showResultRecyclerView.set(true)
        showPaginationProgress.set(false)
    }


    /**
     * the web service error message
     * @param message
     */
    fun onErrorRetroClient() {
        showErrorLayout.set(true)
        showProgressLayout.set(false)
        navigator?.onWebServiceError()

    }

    /**
     * OmdbApi Response
     * @param result Response Of OmdbApiResponse Api
     */
    fun onReady(result: OmdbpiSearchrResponse) {

        onFinishWebService()
        when {
            result.response?.toBoolean() ?: false -> {
                result.search?.let { moviesResult.addAll(it) }
                haveNextPage.set(!(moviesResult.size + 1 == result.totalResults?.toInt()))
            }
            else -> {
                if (requestModel?.page == 1) {
                    showErrorLayout.set(true)
                    showResultRecyclerView.set(false)
                    errorMessage.set(result.error)
                }
            }
        }


    }

    /**
     * On Click Movie Grid Recycler Listener
     * @param position of Grid Recycler
     */
    fun onMoviesItemClick(position: Int) {
        navigator?.onStartDetailMovieActivity(moviesResult.get(position))

    }

    /**
     * Call Webservice For Ger Next Page
     */
    fun callOmdbApiNextPage() {
        when {
            showPaginationProgress.get() == true -> return
            showProgressLayout.get() == true -> return
            haveNextPage.get() == false -> return
        }
        showPaginationProgress.set(true)
        requestModel?.page = requestModel?.page?.plus(1)
        resultPage.set(requestModel?.page)
        getData()
    }

    /**
     * Create Request For Call OmdbApi Webservice
     *
     * @param searchType Movie Or Series
     * @param searchValue Movie Text
     */
    fun searchOmdbApi(searchType: String, searchValue: String) {
        when {
            moviesResult.size > 0 -> moviesResult.clear()
        }
        requestModel = OmdpiRequestModel()
        requestModel?.type = searchType
        requestModel?.searchName = searchValue
        requestModel?.apikey = BuildConfig.API_KEY
        requestModel?.page = 1
        resultPage.set(requestModel?.page)
        onStartWebservice();
        getData()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun clearDisposable() {
        disposable?.clear()
    }

}

