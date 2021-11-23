package com.sinaseyfi.presentation.ui.factories

import androidx.annotation.DrawableRes
import com.sinaseyfi.presentation.R

object RepositoryPrivacyIconFactory {

    @DrawableRes
    fun create(isPrivate: Boolean): Int =
        if(isPrivate) R.drawable.ic_baseline_lock_24
        else R.drawable.ic_baseline_lock_open_24

}