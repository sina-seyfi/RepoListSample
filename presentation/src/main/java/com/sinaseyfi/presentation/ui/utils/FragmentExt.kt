package com.sinaseyfi.presentation.ui.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(@StringRes messageId: Int) {
    this.view?.let { Snackbar.make(it, messageId, Snackbar.LENGTH_SHORT).show() }
}

fun Fragment.showSnackbar(message: String) {
    this.view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
}

fun Fragment.hideKeyboard() {
    val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    var view = view
    if (view == null) {
        view = View(context)
    }
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showKeyboard() {
    val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    var view = view
    if (view == null) {
        view = View(context)
    }
    imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun Fragment.finish() {
    activity?.run { finish() }
}

fun Fragment.runOnUiThread(runnable: () -> Unit = {}) {
    activity?.runOnUiThread { runnable.invoke() }
}