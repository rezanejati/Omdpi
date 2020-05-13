package nejati.me.omdbapi.base

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
abstract class ActivityBaseViewModel<N> : ViewModel(), LifecycleObserver {
    private var disposable: CompositeDisposable? = null

    var navigator: N? = null
    var showProgressLayout = ObservableField(false)
    var showErrorLayout = ObservableField(false)
    var errorMessage = ObservableField<String>()
    var fragmentManager = ObservableField<FragmentManager>()

    init {
        disposable = CompositeDisposable()
    }

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun startNetworkListener() {
        disposable!!.add(ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
                isInternetAvailable(isConnectedToInternet)
            })

    }

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun stopNetworkListener() {
        disposable!!.clear()
    }

    abstract fun isInternetAvailable(isConnectedToInternet: Boolean)

}
