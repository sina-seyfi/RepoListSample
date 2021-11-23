package com.sinaseyfi.presentation.ui.base

import android.annotation.SuppressLint
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePagingAdapter<Model: RecyclerItemModel>(
        dataList: MutableList<Model>? = null,
        // This is the listener for handling click of each item
        var onRecyclerItemClickListener: OnRecyclerItemClickListener<Model>? = null,
        // This is the listener for handling click of nested recycler view item
        var onNestedItemClickListener: OnNestedItemClickListener? = null,
        /*
        This flag will make the adapter to update the list even if it's not changed in data
        This is very useful if your data is not changed, but you want to update your list,
        To change the content of some items
        (See the usages)
         */
        val alwaysUpdateOnSubmit: Boolean = false,
        /*
        Diff utils is automatically set to handler changes in the list
         */
        diffUtil: DiffUtil.ItemCallback<Model> = object: DiffUtil.ItemCallback<Model>() {
            override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean =
                oldItem.viewId == newItem.viewId
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean =
                if(alwaysUpdateOnSubmit) false
                else oldItem == newItem
        }
): PagingDataAdapter<Model, BaseViewHolder<Model>>(diffUtil) {

    override fun onBindViewHolder(holder: BaseViewHolder<Model>, position: Int) {
        holder.onRecyclerItemClickListener = onRecyclerItemClickListener
        holder.onNestedItemClickListener = onNestedItemClickListener
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<Model>) {
        holder.attachedToWindow = true
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Model>) {
        holder.attachedToWindow = false
    }

}