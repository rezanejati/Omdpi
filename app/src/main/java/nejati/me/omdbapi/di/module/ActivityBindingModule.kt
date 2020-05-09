package nejati.me.omdbapi.di.module

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nejati.me.omdbapi.view.activities.MainActivity


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

}
