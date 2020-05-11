package nejati.me.omdbapi.viewModels.movie

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.base.FragmentBaseViewModel
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.view.fragment.movie.MovieNavigator
import nejati.me.omdbapi.webServices.omdpiModel.search.response.detail.Rating
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.OmdbpiSearchrResponse
import nejati.me.omdbapi.webServices.omdpiModel.search.response.search.Search
import nejati.me.sample.di.api.OmdpApi
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class MovieViewModel() : FragmentBaseViewModel<MovieNavigator>() {
    private var disposable: CompositeDisposable? = null
    private var rxSingleSchedulers: RxSingleSchedulers? = null
    private var api: OmdpApi? = null

    init {
        disposable = CompositeDisposable()
    }
    public var requestModel : OmdpiRequestModel ? = null

    /**
     *ListObservable : this is a ObservableArrayList and use in filtering
     */
    var omdbpiResultSearchObservable = ObservableArrayList<Search>()

    var showPaginationProgress = ObservableField<Boolean>()

    val moviesResult = ArrayList<Search>()

    var showWattingSearchLayout = ObservableField(true)
    var showResultRecyclerView = ObservableField(false)


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
        showPaginationProgress.set(false)

        if (result.response!!.toBoolean()){

            moviesResult.addAll(result.search!!)
            setMovieList(moviesResult)

        }else{
            if (requestModel!!.page!! == 1){
                showErrorLayout.set(true)
                showResultRecyclerView.set(false)
                errorMessage.set(result.error)
            }



        }

    }

    fun callOmdbApi() {
        moviesResult.clear()

        showProgressLayout.set(true)
        showWattingSearchLayout.set(false)
        showErrorLayout.set(false)

        getData()

    }
    fun setMovieList(list: List<Search>) {
        omdbpiResultSearchObservable.clear()
        omdbpiResultSearchObservable.addAll(list)



    }

    fun onMoviesItemClick(position: Int) {
        navigator!!.onDetail(omdbpiResultSearchObservable.get(position))

    }

    fun callComicsWebServiceForNextPage() {


        if (showPaginationProgress.get()!!) return
        showPaginationProgress.set(true)
        requestModel!!.page = requestModel!!.page!! +1

        getData()

    }


}

