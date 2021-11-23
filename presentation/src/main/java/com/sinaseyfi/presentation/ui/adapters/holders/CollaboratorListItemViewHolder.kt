package com.sinaseyfi.presentation.ui.adapters.holders

import com.sinaseyfi.presentation.databinding.LayoutCollaboratorListItemBinding
import com.sinaseyfi.presentation.ui.adapters.models.CollaboratorListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.BaseViewHolder
import com.sinaseyfi.presentation.ui.utils.loadFromUrl

class CollaboratorListItemViewHolder(
    private val viewBinding: LayoutCollaboratorListItemBinding
): BaseViewHolder<CollaboratorListRecyclerItemModel>(viewBinding) {
    override fun bind(model: CollaboratorListRecyclerItemModel) {
        viewBinding.apply {
            collaboratorNameTv.text = model.collaboratorName
            avatarSiv.loadFromUrl(model.avatarUrl)
            repoHttpUrlTv.text = model.htmlUrl
        }
    }
}