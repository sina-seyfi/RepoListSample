package com.sinaseyfi.presentation.ui.base

import android.content.Context
import android.content.res.Resources
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.properties.Delegates

abstract class BaseViewHolder<Model: RecyclerItemModel>(viewBinding: ViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

    var onRecyclerItemClickListener: OnRecyclerItemClickListener<Model>? = null
    var onNestedItemClickListener: OnNestedItemClickListener? = null
    var attachedToWindow: Boolean by Delegates.observable(false) { _, oldValue, newValue ->
        if(oldValue != newValue) {
            if(newValue)    onViewAttachedToWindow()
            else            onViewDetachedFromWindow()
        }
    }

    abstract fun bind(model: Model)

    protected open fun onViewDetachedFromWindow() {}
    protected open fun onViewAttachedToWindow() {}

    protected val resources: Resources
        get() = itemView.resources

    protected val packageName: String
        get() = itemView.context.packageName

    protected val context: Context
        get() = itemView.context

}