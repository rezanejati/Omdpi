package nejati.me.omdbapi.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import nejati.me.omdbapi.utility.OmdbApiViewModelFactory
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
abstract class BaseActivity<D : ViewDataBinding, V : ActivityBaseViewModel<*>> :
    AppCompatActivity() {
    protected var dataBinding: D? = null

    protected var viewModel: V? = null

    abstract val bindingVariable: Int

    @set:Inject
    var omdpViewModelFactory: OmdbApiViewModelFactory? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, omdpViewModelFactory).get(getViewModel())

        viewModel!!.fragmentManager.set(supportFragmentManager)

        dataBinding = DataBindingUtil.setContentView(this, layoutRes)

        dataBinding!!.setVariable(bindingVariable, viewModel)

        dataBinding!!.executePendingBindings()

        getLifecycle().addObserver(viewModel!!)


    }

    protected abstract fun getViewModel(): Class<V>

    fun showSnackBar(root: View, message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_LONG)
            .show()
    }


}
