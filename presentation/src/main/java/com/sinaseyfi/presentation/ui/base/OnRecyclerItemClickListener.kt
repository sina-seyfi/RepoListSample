package com.sinaseyfi.presentation.ui.base

interface OnRecyclerItemClickListener<in I: RecyclerItemModel> {
    fun onItemClicked(item: I, viewId: Long = 0, position: Int? = null)
}

interface OnNestedItemClickListener: OnRecyclerItemClickListener<RecyclerItemModel>