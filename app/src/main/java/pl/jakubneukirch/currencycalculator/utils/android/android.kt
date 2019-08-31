package pl.jakubneukirch.currencycalculator.utils.android

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadResource(@DrawableRes drawableId: Int) {
    Glide.with(this)
        .load(drawableId)
        .apply {
            diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        }
        .into(this)
}