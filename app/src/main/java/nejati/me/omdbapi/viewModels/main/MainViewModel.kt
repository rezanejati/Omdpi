package nejati.me.omdbapi.viewModels.main

import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.base.ActivityBaseViewModel
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.view.FragmentModel
import nejati.me.omdbapi.view.activities.mian.MainActivityNavigator
import nejati.me.omdbapi.view.fragment.movie.SearchResultFragment
import nejati.me.sample.di.api.OmdpApi
import javax.inject.Inject


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class MainViewModel() : ActivityBaseViewModel<MainActivityNavigator>() {


    var fragments = ObservableArrayList<FragmentModel>()
    var lastSearch = ObservableField<String>(Prefs.getString("searchPrompt", ""))
    var lastPagerPosition = ObservableField<Int>(Prefs.getInt("pagerPosition", 0))


    /**
     * inject retro client
     */
    @Inject
    constructor(api: OmdpApi, rxSingleSchedulers: RxSingleSchedulers) : this() {

    }

    //There is no guarantee that your onDestroy method will be called at all.
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun onLifeCycleStop() {
        Prefs.putString("searchPrompt", lastSearch.get())
        Prefs.putInt("pagerPosition", lastPagerPosition.get()!!)


    }

    fun addFragmentsIntoViewPager(){
        fragments.add(FragmentModel("Movie", SearchResultFragment.newInstance()))
        fragments.add(FragmentModel("Series", SearchResultFragment.newInstance()))
    }

}


