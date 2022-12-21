package com.ktz.cinephilia.ui.screens.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ktz.cinephilia.data.models.remote.movies.Genre

abstract class BaseAdapter<T : BaseViewHolder<W>, W>() : RecyclerView.Adapter<T>() {

    private var mData: MutableList<W> = mutableListOf()

    private val mGenreList = mutableListOf<Genre>()

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.setData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setData(newList: List<W>) {
        mData = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun setGenreList(genreList: MutableList<Genre>) {
        for (size in 0..1) {
            mGenreList.addAll(genreList)
        }
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
