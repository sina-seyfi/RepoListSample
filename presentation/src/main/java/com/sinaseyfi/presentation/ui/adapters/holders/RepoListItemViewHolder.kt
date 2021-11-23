package com.sinaseyfi.presentation.ui.adapters.holders

import android.content.res.ColorStateList
import androidx.core.content.res.ResourcesCompat
import com.sinaseyfi.presentation.databinding.LayoutRepoListItemBinding
import com.sinaseyfi.presentation.ui.adapters.models.RepoListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.BaseViewHolder
import com.sinaseyfi.presentation.ui.factories.RepositoryPrivacyBackgroundTintColor
import com.sinaseyfi.presentation.ui.factories.RepositoryPrivacyIconFactory
import com.sinaseyfi.presentation.ui.factories.RepositoryPrivacyTintColor

class RepoListItemViewHolder(val viewBinding: LayoutRepoListItemBinding)
    : BaseViewHolder<RepoListRecyclerItemModel>(viewBinding) {
    override fun bind(model: RepoListRecyclerItemModel) {
        viewBinding.apply {
            repoNameTv.text = model.repoName
            privateSiv.apply {
                setImageResource(RepositoryPrivacyIconFactory.create(model.private))
                imageTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources,
                        RepositoryPrivacyTintColor.create(model.private),
                        null
                    )
                )
                backgroundTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources,
                        RepositoryPrivacyBackgroundTintColor.create(model.private),
                        null
                    )
                )
            }
            root.setOnClickListener {
                onRecyclerItemClickListener?.onItemClicked(
                    model
                )
            }
        }
    }
}