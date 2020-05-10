package nejati.me.omdbapi.viewModels.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.base.ActivityBaseViewModel
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
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
class MainViewModel() : ActivityBaseViewModel<MainActivityNavigator>() {
    private var disposable: CompositeDisposable? = null
    private var api: OmdpApi? = null
    private var rxSingleSchedulers: RxSingleSchedulers? = null
    public var requestModel : OmdpiRequestModel ? = null

    init {
        disposable = CompositeDisposable()
    }
    var isMovies = ObservableField(true)

    /**
     *ListObservable : this is a ObservableArrayList and use in filtering
     */
    var omdbpiResultSearchObservable = ObservableArrayList<Search>()


    //todo change this
    val moviesResult = ArrayList<Search>()
    val seriesResult = ArrayList<Search>()
    var showProgressLayout = ObservableField(false)
    var showWattingSearchLayout = ObservableField(true)
    var showResultRecyclerView = ObservableField(false)
    var showErrorLayout = ObservableField(false)
    var errorMessage =  ObservableField<String>()

    /**
     * inject retro client
     */
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
        showProgressLayout.set(false)
    }

    /**
     * OmdbApi Response
     * @param result Response Of OmdbApiResponse Api
     */
    fun onReady(result: OmdbpiSearchrResponse) {

        showProgressLayout.set(false)
        showResultRecyclerView.set(true)

        if (result.response!!.toBoolean()){
            if(requestModel!!.type.equals("movie")){
                moviesResult.clear()
                moviesResult.addAll(result.search!!)
                setMovieList(moviesResult)
            }else{
                seriesResult.clear()
                seriesResult.addAll(result.search!!)
                setMovieList(seriesResult)
            }


        }else{
            showErrorLayout.set(true)
            showResultRecyclerView.set(false)
            errorMessage.set(result.error)


        }

    }
    fun callOmdbApi() {

        showProgressLayout.set(true)
        showWattingSearchLayout.set(false)
        showErrorLayout.set(false)

        getData()

    }
    fun setMovieList(list: List<Search>) {
        if (omdbpiResultSearchObservable.size>0)  {omdbpiResultSearchObservable.clear()}

        omdbpiResultSearchObservable.addAll(list)

    }

    fun onMoviesItemClick(position: Int) {
        navigator!!.onDetail(omdbpiResultSearchObservable.get(position))

    }


    fun onClickMovies(){
        isMovies.set(true)
        if (moviesResult.size>0)
            setMovieList(moviesResult)
        else {
            requestModel?.let {
                it.type="movie"
                callOmdbApi()
            }
        }
    }
    fun onClickSeries(){
        isMovies.set(false)
        if (seriesResult.size>0)
            setMovieList(seriesResult)
        else {
            requestModel?.let {
                it.type="series"
                callOmdbApi()
            }
        }
    }
}

