package nejati.me.omdbapi.view.activities.detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_detail_movie_activity.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseActivity
import nejati.me.omdbapi.databinding.ActivityDetailMovieActivityBinding
import nejati.me.omdbapi.viewModels.detailActivity.DetailViewModel


class DetailMovieActivity() :  BaseActivity<ActivityDetailMovieActivityBinding, DetailViewModel>(),
    DetailMovieActivityNavigator{

    /**
     * Set Variable fot DataBinding
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

        viewModel!!.navigator = this

        initializeActionBar()

        val bundle=intent.extras
        bundle?.let {
            viewModel!!.getDetailMovie(it.getString("ImdbApi")!!)

        }

    }
    fun initializeActionBar(){
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
        setTitle(intent.getStringExtra("title"))
        collapsing_toolbar.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onWebServiceError() {
        showSnackBar(dataBinding!!.root,getString(R.string.webservice_not_available))
    }

    override fun onWebServiceMessage(message: String) {
        showSnackBar(dataBinding!!.root,message)
    }

}
