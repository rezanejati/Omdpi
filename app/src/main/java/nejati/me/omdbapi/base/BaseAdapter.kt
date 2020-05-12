package nejati.me.omdbapi.base

import android.view.View
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView

/**
 * Authors:
 * Reza Nejati <rn.nejati></rn.nejati>@gmail.com>
 * Copyright © 2019
 */
abstract class BaseAdapter<T : RecyclerView.ViewHolder?, D> :
    RecyclerView.Adapter<T>() {
    public fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }
}