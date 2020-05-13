package nejati.me.omdbapi.viewModels.mainActivity

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.pixplicity.easyprefs.library.Prefs
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.base.ActivityBaseViewModel
import nejati.me.omdbapi.model.FragmentModel
import nejati.me.omdbapi.view.activities.mian.MainActivityNavigator
import nejati.me.omdbapi.view.fragment.movie.MovieFragment
import nejati.me.sample.di.api.OmdpApi
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
class MainViewModel() : ActivityBaseViewModel<MainActivityNavigator>() {

    var fragments = ObservableArrayList<FragmentModel>()

    var lastSearch = ObservableField<String>(Prefs.getString("searchPrompt", ""))

    var lastPagerPosition = ObservableField<Int>(Prefs.getInt("pagerPosition", 0))


    /**
     * Inject Retro Client
     * You Can Access All Api in Constructor
     */
    @Inject
    constructor(api: OmdpApi, rxSingleSchedulers: RxSingleSchedulers) : this() {
        //Todo Call Main Activity Web Service
    }

    /**
     * Save User Search Text and View Pager Position For Next Time
     *
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun saveSearchText() {
        Prefs.putString("searchPrompt", lastSearch.get())
        Prefs.putInt("pagerPosition", lastPagerPosition.get()!!)
    }

    /**
     * Add Fragments Into MainActivity ViewPager
     *
     */
    fun addFragmentsIntoViewPager() {

        when {
            fragments.size > 0 -> return
        }
        val movieModel = FragmentModel()
        movieModel.title = "Movie"
        movieModel.fragment = MovieFragment.newInstance()
        fragments.add(movieModel)

        val seriesModel = FragmentModel()
        seriesModel.title = "Series"
        seriesModel.fragment = MovieFragment.newInstance()
        fragments.add(seriesModel)

    }

    override fun isInternetAvailable(isConnectedToInternet: Boolean) {
        navigator!!.onNetworkStatus(isConnectedToInternet)
    }

}


