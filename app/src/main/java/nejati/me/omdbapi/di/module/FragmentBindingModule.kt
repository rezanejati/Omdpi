package nejati.me.omdbapi.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nejati.me.omdbapi.view.fragment.movie.SearchFragment


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindMovieFragment(): SearchFragment

}
