package nejati.me.omdbapi.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nejati.me.omdbapi.view.activities.detail.DetailMovieActivity
import nejati.me.omdbapi.view.activities.mian.MainActivity


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindDetailMovieActivity(): DetailMovieActivity


}
