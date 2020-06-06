package com.ranjeetwaje.g_shopping.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranjeetwaje.g_shopping.R
import com.ranjeetwaje.g_shopping.databinding.GrocceryItemsBinding
import com.ranjeetwaje.g_shopping.network.GroceryData

class GroceryListAdapter : RecyclerView.Adapter<GroceryListDataViewHolder>() {

    var itemList: List<GroceryData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryListDataViewHolder {
        val withDataBinding: GrocceryItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            GroceryListDataViewHolder.LAYOUT,
            parent,
            false)
        return GroceryListDataViewHolder(withDataBinding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: GroceryListDataViewHolder, position: Int) {
        holder.binding.also {
            it.items = itemList[position]
        }
    }
}
class GroceryListDataViewHolder(val binding: GrocceryItemsBinding): RecyclerView.ViewHolder(binding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.groccery_items
    }
}
