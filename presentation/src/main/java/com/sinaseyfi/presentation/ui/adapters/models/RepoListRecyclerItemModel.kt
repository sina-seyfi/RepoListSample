package com.sinaseyfi.presentation.ui.adapters.models

import com.sinaseyfi.presentation.ui.base.RecyclerItemModel

data class RepoListRecyclerItemModel(
    override val viewId: Long,
    val repoName: String,
    val ownerName: String,
    val private: Boolean,
    val description: String
): RecyclerItemModel