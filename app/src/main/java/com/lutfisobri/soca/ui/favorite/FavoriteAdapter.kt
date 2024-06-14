package com.lutfisobri.soca.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.databinding.ItemResultBinding

class FavoriteAdapter: PagingDataAdapter<HistoryResponseResult, FavoriteAdapter.ViewHolder>(DIFF_CALLBACK) {
    var onItemClick: (HistoryResponseResult) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item, onItemClick)
    }

    class ViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryResponseResult, onItemClick: (HistoryResponseResult) -> Unit) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(item.image)
                    .into(ivIcon)
                tvTitle.text = item.label
                root.setOnClickListener { onItemClick.invoke(item) }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryResponseResult>() {
            override fun areItemsTheSame(oldItem: HistoryResponseResult, newItem: HistoryResponseResult): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HistoryResponseResult, newItem: HistoryResponseResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}