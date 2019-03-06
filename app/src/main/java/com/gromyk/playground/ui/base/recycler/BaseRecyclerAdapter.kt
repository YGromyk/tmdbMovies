/**
 * Created by Yurii Gromyk
 * @date 3/6/19 10:27 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.ui.base.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> :
    RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<T>>() {

    val items: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindView(items[position])
    }

    fun replaceItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
    }


    abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(item: T)
    }
}