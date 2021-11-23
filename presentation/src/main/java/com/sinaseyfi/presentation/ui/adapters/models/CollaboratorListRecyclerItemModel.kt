package com.sinaseyfi.presentation.ui.adapters.models

import com.sinaseyfi.presentation.ui.base.RecyclerItemModel

data class CollaboratorListRecyclerItemModel(
    override val viewId: Long,
    val avatarUrl: String,
    val collaboratorName: String,
    val htmlUrl: String,
): RecyclerItemModel