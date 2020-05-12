package nejati.me.omdbapi.module

import dagger.Module
import dagger.Provides
import nejati.me.omdbapi.api.RxSingleSchedulers
import nejati.me.omdbapi.di.scope.CustomScope

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
@Module
class RxModule {

    @CustomScope
    @Provides
    fun providesScheduler(): RxSingleSchedulers {
        return RxSingleSchedulers.DEFAULT
    }

}
