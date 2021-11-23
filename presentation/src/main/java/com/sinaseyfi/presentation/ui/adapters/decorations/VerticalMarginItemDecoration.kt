package com.sinaseyfi.presentation.ui.adapters.decorations

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.sinaseyfi.presentation.R

class VerticalMarginItemDecoration(
    val context: Context,
    @DimenRes marginId: Int = R.dimen.list_item_vertical_margin
): RecyclerView.ItemDecoration() {

    private val verticalMargin = context.resources.getDimensionPixelSize(marginId)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(0, verticalMargin, 0, 0)
    }

}