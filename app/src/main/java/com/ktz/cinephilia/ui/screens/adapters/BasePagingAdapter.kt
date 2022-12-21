package com.ktz.cinephilia.ui.screens.adapters

import android.view.View
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ktz.cinephilia.data.models.entities.moviesList.BasePagingModel

abstract class BasePagingAdapter<T : BasePagingModel, W : BasePagingViewHolder<T>> : PagingDataAdapter<T, W>(MoviesDiffCallBack()) {

    override fun onBindViewHolder(holder: W, position: Int) {
        val data = getItem(position)
        holder.bindData(data!!)
    }

    protected class MoviesDiffCallBack<T : BasePagingModel> : DiffUtil.ItemCallback<T>(){
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            TODO("Not yet implemented")
        }
    }
}

abstract class BasePagingViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindData(data: T)
}
 