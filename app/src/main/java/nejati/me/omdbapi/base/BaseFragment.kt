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

/* Authors:
* Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
* Copyright © 2020
*/
abstract class BaseFragment<T : ViewDataBinding, V : FragmentBaseViewModel<*>> : Fragment() {

    @set:Inject
    var omdbapiViewModelFactory: OmdbApiViewModelFactory? = null

    var baseActivity: BaseActivity<*, *>? = null

    private var mRootView: View? = null

    var viewDataBinding: T? = null

    protected var viewModel: V? = null

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutRes: Int

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
        viewModel = ViewModelProviders.of(this, omdbapiViewModelFactory).get(getViewModel())

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
        getLifecycle().addObserver(viewModel!!)

    }


    fun showSnackBar(root: View, message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_LONG)
            .show()
    }

}
