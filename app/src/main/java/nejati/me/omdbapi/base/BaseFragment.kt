/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package nejati.me.omdbapi.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import nejati.me.omdbapi.utility.OmdbApiViewModelFactory
import javax.inject.Inject

/**
 * Created by amitshekhar on 09/07/17.
 */

abstract class BaseFragment<T : ViewDataBinding, V : FragmentBaseViewModel<*>> : Fragment() {

    @set:Inject
    var comicsViewModelFactory: OmdbApiViewModelFactory? = null

    var baseActivity: BaseActivity<*, *>? = null

    private var mRootView: View? = null

    var viewDataBinding: T? = null
/*
        private set
*/

    protected var viewModel: V?=null

    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutRes: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */

    protected abstract fun getViewModel(): Class<V>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.baseActivity = context

        }




    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,comicsViewModelFactory).get(getViewModel())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        mRootView = viewDataBinding!!.root
        return mRootView
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding!!.setVariable(bindingVariable, viewModel)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.executePendingBindings()
        getLifecycle().addObserver(viewModel!!);

    }


    fun showSnackBar( root : View,message : String){
        Snackbar.make(root,message, Snackbar.LENGTH_LONG)
            .show()
    }



}
