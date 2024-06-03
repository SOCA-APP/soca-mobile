package com.lutfisobri.soca.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lutfisobri.soca.data.models.ItemMenuModel
import com.lutfisobri.soca.databinding.ItemMenuDashboardBinding

class ItemMenuAdapter(private val itemMenus: List<ItemMenuModel>): RecyclerView.Adapter<ItemMenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = ItemMenuDashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemMenus[position])
    }

    override fun getItemCount(): Int = itemMenus.size

    class ViewHolder(private val binding: ItemMenuDashboardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(itemMenu: ItemMenuModel) {
            binding.ivIcon.setImageResource(itemMenu.icon)
            binding.tvTitle.text = itemMenu.title
            binding.root.setOnClickListener { itemMenu.action() }
        }
    }
}