package nejati.me.omdbapi.base

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
abstract class FragmentBaseViewModel<N> : ViewModel(), LifecycleObserver {

    var showProgressLayout = ObservableField(false)
    var showErrorLayout = ObservableField(false)
    var errorMessage = ObservableField<String>()

    var navigator: N? = null

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
