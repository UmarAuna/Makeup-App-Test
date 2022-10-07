package com.makeup.makeupapptest.util

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.makeup.makeupapptest.R

/*
* Usage
* view.mediaImageView.loadImageWithGlide(url, R.drawable.media_image_placeholder)
* binding.explanationImage.loadImageWithGlide(explanationImageUrl)
* */
fun ImageView.loadImageWithGlide(
    imageUrl: String? = null,
    @DrawableRes placeholder: Int? = null,
    requestOptions: RequestOptions = RequestOptions.fitCenterTransform()
) {
    loadImageWithGlide(imageUrl, placeholder, requestOptions) {
        load(imageUrl)
    }
}

// super function
private fun ImageView.loadImageWithGlide(
    tag: Any?,
    @DrawableRes placeholder: Int? = null,
    requestOptions: RequestOptions = RequestOptions.fitCenterTransform(),
    load: RequestManager.() -> RequestBuilder<Drawable>
) {
    setTag(R.id.glide_tag_key, tag)

    Glide
        .with(this)
        .load()
        .also {
            if (placeholder != null) {
                it.placeholder(placeholder).error(placeholder)
            }
        }
        .apply(requestOptions)
        .into(this)
}
