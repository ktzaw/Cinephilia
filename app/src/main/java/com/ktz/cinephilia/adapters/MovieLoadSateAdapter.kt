package com.ktz.cinephilia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ktz.cinephilia.databinding.ListItemLoadingBinding

class MovieLoadSateAdapter : LoadStateAdapter<MovieLoadSateAdapter.LoadingGridStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadingGridStateViewHolder, loadState: LoadState) {

        if (loadState is LoadState.Error) {
            Toast.makeText(holder.itemView.context, "${loadState.error}", Toast.LENGTH_LONG).show()
        }

        holder.binding.itemProgressBar.isVisible = loadState is LoadState.Loading

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingGridStateViewHolder {
        return LoadingGridStateViewHolder(

            ListItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    class LoadingGridStateViewHolder(val binding: ListItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

}