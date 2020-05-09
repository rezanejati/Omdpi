package nejati.me.omdbapi.viewModels.main

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.base.ActivityBaseViewModel
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.view.activities.MainActivityNavigator
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
    var requestModel = OmdpiRequestModel()

    init {
        disposable = CompositeDisposable()
    }
    var isMovies = ObservableField(true)

    /**
     *ListObservable : this is a ObservableArrayList and use in filtering
     */
    var omdbpiResultSearchObservable = ObservableArrayList<Search>()

    /**
     * ListObservable : this is a MutableLiveData and use in adapter
     */
    val omdbpiResultSearchLiveData = MutableLiveData<List<Search>>()

    /**
     * MovieList : this object is the list of Movies
     */

    //todo change this

    val moviesResult = ArrayList<Search>()
    var showProgressLayout = ObservableField(false)
    var showWattingSearchLayout = ObservableField(true)
    var showResultRecyclerView = ObservableField(false)

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
        disposable!!.add(api!!.getMovies(requestModel)
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
     * @param t Response Of OmdbApiResponse Api
     */
    fun onReady(result: OmdbpiSearchrResponse) {
        showProgressLayout.set(false)
        showResultRecyclerView.set(true)


        Log.e("response",result.response!!.toLowerCase())
        if (result.response!!.toLowerCase().toBoolean()){
            if (moviesResult.size>0)
                moviesResult.clear()

            moviesResult.addAll(result.search!!)
            omdbpiResultSearchLiveData.setValue(moviesResult)
        }else{


        }

    }
    fun callOmdbApi() {

        showProgressLayout.set(true)
        showWattingSearchLayout.set(false)
        getData()

    }


    fun onMoviesItemClick(position: Int) {
        navigator!!.onDetail(omdbpiResultSearchObservable.get(position))

    }
    fun setMovieList(list: List<Search>) {

        omdbpiResultSearchObservable.clear()

        omdbpiResultSearchObservable.addAll(list)

    }

    fun onClickMovies(){
        isMovies.set(true)

    }

    fun onClickSeries(){
        isMovies.set(false)

    }

}

