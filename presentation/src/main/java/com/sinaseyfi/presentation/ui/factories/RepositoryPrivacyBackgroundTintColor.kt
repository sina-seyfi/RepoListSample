package com.sinaseyfi.presentation.ui.factories

import androidx.annotation.ColorRes
import com.sinaseyfi.presentation.R

object RepositoryPrivacyBackgroundTintColor {

    @ColorRes
    fun create(isPrivate: Boolean): Int =
        if(isPrivate) R.color.red_background
        else R.color.green_background

}