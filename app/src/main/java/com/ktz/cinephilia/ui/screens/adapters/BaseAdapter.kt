package com.ktz.cinephilia.ui.screens.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : BaseViewHolder<W>, W>() : RecyclerView.Adapter<T>() {

    private var mData: MutableList<W> = ArrayList()

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.mData = mData[position]
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setData(newList: List<W>) {
        mData = newList.toMutableList()
        notifyDataSetChanged()
    }
}

abstract class BaseViewHolder<W>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mData: W? = null
        set(value) {
            field = value
            value?.let {
                setData(it)
            }
        }

    abstract fun setData(data: W)
}
