package com.sinaseyfi.presentation.ui.factories

import androidx.annotation.ColorRes
import com.sinaseyfi.presentation.R

object RepositoryPrivacyTintColor {

    @ColorRes
    fun create(isPrivate: Boolean): Int =
        if(isPrivate) R.color.red
        else R.color.green

}