package com.fullstack.catawiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fullstack.catawiki.databinding.CatItemBinding
import com.fullstack.catawiki.models.CatItem


class CatsListAdapter : ListAdapter<CatItem, CatsListAdapter.CatViewHolder>(Companion) {
    //class CatViewHolder(val binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root)
    class CatViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: CatItemBinding?

        init {
            binding = DataBindingUtil.bind(v)
        }
    }

    companion object : DiffUtil.ItemCallback<CatItem>() {
        override fun areItemsTheSame(oldItem: CatItem, newItem: CatItem): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: CatItem, newItem: CatItem): Boolean =
            oldItem.name == newItem.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(layoutInflater)
        return CatViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding?.catItem= it
            holder.binding?.executePendingBindings()
        }
    }
}