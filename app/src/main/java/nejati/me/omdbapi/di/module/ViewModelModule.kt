package nejati.me.omdbapi.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nejati.me.omdbapi.di.scope.ViewModelKey
import nejati.me.omdbapi.utility.OmdbApiViewModelFactory
import nejati.me.omdbapi.viewModels.detailActivity.DetailViewModel
import nejati.me.omdbapi.viewModels.mainActivity.MainViewModel
import nejati.me.omdbapi.viewModels.movieFragment.MovieViewModel

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun omdbViewModelFactory(factory: OmdbApiViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun movieViewModel(movieViewModel: MovieViewModel): ViewModel
}
