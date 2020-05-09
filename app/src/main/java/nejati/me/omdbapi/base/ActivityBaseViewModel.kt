package nejati.me.omdbapi.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
abstract class ActivityBaseViewModel<N> : ViewModel() {

    var navigator: N? = null
}
