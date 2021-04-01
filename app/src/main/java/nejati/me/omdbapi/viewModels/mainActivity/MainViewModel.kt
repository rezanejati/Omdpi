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
import nejati.me.omdbapi.view.fragment.movie.SearchFragment
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

    var viewPagerPosition = ObservableField<Int>(Prefs.getInt("lastPagerPosition", 0))

    /**
     * Inject Retro Client
     * You Can Access All Api in Constructor
     */
    @Inject
    constructor(api: OmdpApi, rxSingleSchedulers: RxSingleSchedulers) : this() {
        // Todo Call Main Activity Web Service
    }

    /**
     * Save User Search movie and View Pager Position For Next Time
     *
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun saveSearchState() {
        Prefs.putString("searchPrompt", lastSearch.get())
        Prefs.putInt("lastPagerPosition", viewPagerPosition.get()?:0)
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
        movieModel.fragment = SearchFragment.newInstance()
        fragments.add(movieModel)

        val seriesModel = FragmentModel()
        seriesModel.title = "Series"
        seriesModel.fragment = SearchFragment.newInstance()
        fragments.add(seriesModel)
    }

    /**
     *
     * @param isConnectedToInternet
     */
    override fun isInternetAvailable(isConnectedToInternet: Boolean) {
        navigator?.onNetworkStatus(isConnectedToInternet)
    }

    /**
     *
     * @param position View Pager
     */
    fun onPageSelected(position: Int) {
        viewPagerPosition.set(position)
    }
}
