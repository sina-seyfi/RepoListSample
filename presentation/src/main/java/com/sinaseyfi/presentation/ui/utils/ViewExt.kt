package com.sinaseyfi.presentation.ui.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.sinaseyfi.presentation.R

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.setVisibility(visible: Boolean) {
    if (visible) show() else hide()
}

fun View.setInvisibility(invisible: Boolean) {
    if(invisible) invisible() else show()
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE

val View.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this.context)

fun List<View>.setOnClickListener(action: (View) -> Unit) {
    this.forEach { view -> view.setOnClickListener { action(it) } }
}

fun List<View>.setOnClickListener(listener: View.OnClickListener?) {
    this.forEach { view ->
        view.setOnClickListener(listener)
    }
}

fun List<View?>.setVisibility(visible: Boolean) {
    forEach { view -> view?.setVisibility(visible) }
}

fun List<View?>.setEnabled(enabled: Boolean) {
    forEach { view -> view?.isEnabled = enabled }
}

fun View.getNavigationBarHeight(): Int {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else 0
}

fun List<View?>.animate(chain: ViewPropertyAnimator.() -> Unit) {
    this.forEach { view -> view?.animate()?.chain() }
}

fun ImageView?.loadFromUrl(url: String?, actionFinished: () -> Unit = {}) {
    this?.let { iv ->
        if(url != null) {
            try {
                Glide.with(iv.context).asBitmap().load(GlideUrl(url)).placeholder(R.drawable.ic_baseline_face_24).into(
                    object: BitmapImageViewTarget(iv) {
                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            actionFinished.invoke()
                            super.onLoadFailed(errorDrawable)
                        }
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            actionFinished.invoke()
                            super.onResourceReady(resource, transition)
                        }
                    }
                )
            } catch (e: Exception) {
                iv.setImageResource(R.drawable.ic_baseline_face_24)
            }
        } else {
            iv.setImageResource(R.drawable.ic_baseline_face_24)
        }
    }
}