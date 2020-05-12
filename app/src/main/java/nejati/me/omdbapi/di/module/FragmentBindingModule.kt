package nejati.me.omdbapi.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nejati.me.omdbapi.view.fragment.movie.SearchResultFragment


/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i></reza.n.j.t.i>@gmail.com>
 * Copyright © 2017
 */
@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindMovieFragment(): SearchResultFragment

}
