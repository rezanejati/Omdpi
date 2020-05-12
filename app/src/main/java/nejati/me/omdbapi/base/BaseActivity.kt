package nejati.me.omdbapi.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProviders
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import nejati.me.omdbapi.R
import nejati.me.omdbapi.utility.OmdbApiViewModelFactory
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
abstract class BaseActivity<D : ViewDataBinding, V : ActivityBaseViewModel<*>> : AppCompatActivity()
{
    protected var dataBinding: D?=null

    protected var viewModel: V?=null

    abstract val bindingVariable: Int

    @set:Inject
    var omdpViewModelFactory: OmdbApiViewModelFactory? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onStart() {
        super.onStart()
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this,omdpViewModelFactory).get(getViewModel())
        viewModel!!.fragmentManager.set(supportFragmentManager)
        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding!!.setVariable(bindingVariable, viewModel)
        dataBinding!!.executePendingBindings()
        getLifecycle().addObserver(viewModel!!)


        //Network listener
        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
                if(!isConnectedToInternet)showSnackBar(dataBinding!!.root,getString(R.string.network_not_avilable)) }
    }
    protected abstract fun getViewModel(): Class<V>

    fun showSnackBar( root : View,message : String){
        Snackbar.make(root,message, Snackbar.LENGTH_LONG)
            .show()
    }


}
