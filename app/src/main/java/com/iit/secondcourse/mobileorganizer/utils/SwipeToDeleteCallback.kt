package com.iit.secondcourse.mobileorganizer.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class SwipeToDeleteCallback(private val adapter: ItemTouchHelperAdapter) :
    ItemTouchHelper.Callback() {
    override fun isLongPressDragEnabled() = false

    override fun isItemViewSwipeEnabled() = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
        adapter.onRowSwiped(viewHolder.bindingAdapterPosition)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        viewHolder1: RecyclerView.ViewHolder
    ) = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.END //or ItemTouchHelper.START
        return makeMovementFlags(0, swipeFlags)
    }

    interface ItemTouchHelperAdapter {
        fun onRowSwiped(position: Int)
    }
}