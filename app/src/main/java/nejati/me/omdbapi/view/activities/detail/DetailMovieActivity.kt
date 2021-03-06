package nejati.me.omdbapi.view.activities.detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_detail_movie_activity.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseActivity
import nejati.me.omdbapi.databinding.ActivityDetailMovieActivityBinding
import nejati.me.omdbapi.view.fragment.movie.SearchFragment
import nejati.me.omdbapi.viewModels.detailActivity.DetailViewModel

class DetailMovieActivity :
    BaseActivity<ActivityDetailMovieActivityBinding, DetailViewModel>(),
    DetailMovieActivityNavigator {

    /**
     * Set Variable For DataBinding
     * @return
     */
    override val bindingVariable: Int
        get() = BR.viewModel

    /**
     * Set Layout For Activity
     * @return
     */
    override val layoutRes: Int
        get() = R.layout.activity_detail_movie_activity

    /**
     * Add View Model
     * @return
     */
    override fun getViewModel(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel?.navigator = this

        initializeActionBar()

        val bundle = intent.extras
        bundle?.let {
            viewModel?.getDetailMovie(it.getString(SearchFragment.EXTRA_IMDB_ID)?:"")
        }
    }

    private fun initializeActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = intent.getStringExtra(SearchFragment.EXTRA_TITLE)
        collapsing_toolbar.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onWebServiceError() {
        showSnackBar(dataBinding?.root, getString(R.string.webservice_not_available))
    }

    override fun onWebServiceMessage(message: String) {
        showSnackBar(dataBinding?.root, message)
    }

    override fun onNetworkStatus(isConnectedToInternet: Boolean) {
        when {
            !isConnectedToInternet -> showSnackBar(
                dataBinding?.root,
                getString(R.string.network_not_available)
            )
        }
    }
}
