package nejati.me.omdbapi.base

import android.app.Application


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */


class BaseApplication : Application() {

    companion object {
        private lateinit var app: BaseApplication
        fun get(): BaseApplication = app
    }
}