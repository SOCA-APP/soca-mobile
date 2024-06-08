package com.lutfisobri.soca.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lutfisobri.soca.data.models.ItemResultModel
import com.lutfisobri.soca.databinding.ItemResultBinding

class FavoriteAdapter(private val items: List<ItemResultModel>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    var onItemClick: (ItemResultModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemResultModel, onItemClick: (ItemResultModel) -> Unit) {
            with(binding) {
                ivIcon.setImageResource(item.icon)
                tvTitle.text = item.title
                root.setOnClickListener { onItemClick.invoke(item) }
            }
        }
    }
}