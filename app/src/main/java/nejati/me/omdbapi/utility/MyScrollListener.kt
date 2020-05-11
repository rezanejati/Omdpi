package nejati.me.omdbapi.utility

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
abstract class MyScrollListener(context: Context) : RecyclerView.OnScrollListener() {

    private var toolbarOffset = 0

    private val toolbarHeight: Int

    init {
        val actionBarAttr = intArrayOf(android.R.attr.actionBarSize)
        val a = context.obtainStyledAttributes(actionBarAttr)
        toolbarHeight = a.getDimension(0, 0f).toInt() + 10
        a.recycle()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        clipToolbarOffset()

        onMoved(toolbarOffset,dy)

        if (toolbarOffset < toolbarHeight && dy > 0 || toolbarOffset > 0 && dy < 0) {
            toolbarOffset += dy
        }

        if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition() ==
            (recyclerView.layoutManager as LinearLayoutManager?)!!.itemCount - 1) {
            onEnd()
        }

        if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstCompletelyVisibleItemPosition()== 0){
            onFirst()
        }
    }

    private fun clipToolbarOffset() {
        if (toolbarOffset > toolbarHeight) {
            toolbarOffset = toolbarHeight
        } else if (toolbarOffset < 0) {
            toolbarOffset = 0
        }
    }

    abstract fun onMoved(distance: Int, dy: Int)

    abstract fun onEnd()

    abstract fun onFirst()

}