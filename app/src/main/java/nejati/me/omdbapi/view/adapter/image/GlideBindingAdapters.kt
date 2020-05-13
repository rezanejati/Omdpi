package nejati.me.sample.view.adapter

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import nejati.me.omdbapi.R

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
object GlideBindingAdapters {

    /**
     *
     * @param view
     * @param imageUrl
     */
    @JvmStatic
    @BindingAdapter("moviesImage")
    fun loadImage(view: ImageView, imageUrl: String) {
        if (TextUtils.isEmpty(imageUrl)) return
        val options: RequestOptions = RequestOptions()
            .centerInside()
            .error(R.drawable.poster_not_found)
        Glide.with(view.context).load(imageUrl).apply(options).into(view)
    }

    /**
     * Make Blur Image
     *
     * @param view
     * @param imageUrl
     */
    @JvmStatic
    @BindingAdapter("blurelImage")
    fun loadDetailImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context).load(imageUrl).apply(
            bitmapTransform(
                BlurTransformation(15, 3)
            )
        ).into(view)

    }

    @JvmStatic
    @BindingAdapter("drwableImage")
    fun loadBackgroundImage(view: ImageView, drwable: Drawable) {
        Glide.with(view.context)
            .load(drwable)
            .apply(bitmapTransform(BlurTransformation(15, 1)))
            .into(view)

    }

}
