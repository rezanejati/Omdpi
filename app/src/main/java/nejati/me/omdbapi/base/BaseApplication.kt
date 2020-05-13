package nejati.me.omdbapi.base

import android.app.Activity
import android.app.Application
import android.content.ContextWrapper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.pixplicity.easyprefs.library.Prefs
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import nejati.me.omdbapi.di.component.DaggerApplicationComponent
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
 * Copyright © 2020
 */
class BaseApplication : Application(), HasActivityInjector, LifecycleObserver,
    HasSupportFragmentInjector {

    @set : Inject
    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null

    @set : Inject
    var fragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    override fun onCreate() {
        super.onCreate()



        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment>? {
        return fragmentInjector
    }
}