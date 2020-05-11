package nejati.me.omdbapi.view.activities.detail

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.activity_detail_movie_activity.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import nejati.me.omdbapi.BR
import nejati.me.omdbapi.BuildConfig
import nejati.me.omdbapi.R
import nejati.me.omdbapi.base.BaseActivity
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.databinding.ActivityDetailMovieActivityBinding
import nejati.me.omdbapi.service.model.request.OmdpiRequestModel
import nejati.me.omdbapi.viewModels.detail.DetailViewModel


class DetailMovieActivity() :  BaseActivity<ActivityDetailMovieActivityBinding, DetailViewModel>(),
    DetailMovieActivityNavigator{


    override val bindingVariable: Int
        get() = BR.viewModel

    /**
     * Set Layout For Activity
     *
     * @return
     */
    override val layoutRes: Int
        get() = R.layout.activity_detail_movie_activity

    /**
     * Add View Model
     *
     * @return
     */
    override fun getViewModel(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel!!.navigator = this
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);

        setTitle(intent.getStringExtra("title"))
        val OmdpiRequestModel = OmdpiRequestModel()
        OmdpiRequestModel.apikey = BuildConfig.API_KEY
        OmdpiRequestModel.imdbId = intent.getStringExtra("ImdbApi")
        viewModel!!.requestModel=OmdpiRequestModel
        viewModel!!.callOmdbApi()
        collapsing_toolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
