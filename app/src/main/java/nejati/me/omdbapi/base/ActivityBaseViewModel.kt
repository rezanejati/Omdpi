package nejati.me.omdbapi.base

import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.sample.di.api.OmdpApi
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
abstract class ActivityBaseViewModel<N> : ViewModel(),LifecycleObserver {

    var navigator: N? = null
    var showProgressLayout = ObservableField(false)
    var showErrorLayout = ObservableField(false)
    var errorMessage =  ObservableField<String>()
    var fragmentManager =  ObservableField<FragmentManager>()


}
