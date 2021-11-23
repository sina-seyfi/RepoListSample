package com.sinaseyfi.presentation.ui.adapters

import android.view.ViewGroup
import com.sinaseyfi.presentation.databinding.LayoutCollaboratorListItemBinding
import com.sinaseyfi.presentation.ui.adapters.holders.CollaboratorListItemViewHolder
import com.sinaseyfi.presentation.ui.adapters.models.CollaboratorListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.BasePagingAdapter
import com.sinaseyfi.presentation.ui.base.BaseViewHolder
import com.sinaseyfi.presentation.ui.utils.layoutInflater

class CollaboratorPagingAdapter: BasePagingAdapter<CollaboratorListRecyclerItemModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CollaboratorListRecyclerItemModel> =
        CollaboratorListItemViewHolder(
            LayoutCollaboratorListItemBinding.inflate(
                parent.layoutInflater,
                parent,
                false
            )
        )
}