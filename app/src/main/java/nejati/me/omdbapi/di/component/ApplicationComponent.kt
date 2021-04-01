package nejati.me.omdbapi.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.di.module.ActivityBindingModule
import nejati.me.omdbapi.di.module.ApplicationModule
import nejati.me.omdbapi.di.module.FragmentBindingModule
import nejati.me.omdbapi.di.scope.CustomScope
import nejati.me.omdbapi.module.ApiModule
import nejati.me.omdbapi.module.RxModule

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
@CustomScope
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class, FragmentBindingModule::class,
        ApiModule::class,
        RxModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    override fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): ApplicationComponent
    }
}
