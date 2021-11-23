package com.sinaseyfi.presentation.ui.adapters

import android.view.ViewGroup
import com.sinaseyfi.presentation.databinding.LayoutRepoListItemBinding
import com.sinaseyfi.presentation.ui.adapters.holders.RepoListItemViewHolder
import com.sinaseyfi.presentation.ui.adapters.models.RepoListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.BaseAdapter
import com.sinaseyfi.presentation.ui.base.BaseViewHolder
import com.sinaseyfi.presentation.ui.base.OnRecyclerItemClickListener
import com.sinaseyfi.presentation.ui.utils.layoutInflater

class RepoListAdapter(
    onRecyclerItemClickListener: OnRecyclerItemClickListener<RepoListRecyclerItemModel>
): BaseAdapter<RepoListRecyclerItemModel>(onRecyclerItemClickListener = onRecyclerItemClickListener) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RepoListRecyclerItemModel> =
        RepoListItemViewHolder(
            LayoutRepoListItemBinding.inflate(
                parent.layoutInflater,
                parent,
                false
            )
        )

}