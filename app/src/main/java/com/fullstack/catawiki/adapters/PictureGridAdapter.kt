package com.fullstack.catawiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fullstack.catawiki.R
import com.fullstack.catawiki.models.CatItem


class PictureGridAdapter(val myDataset: List<CatItem>, val listener: ItemClickListener) :
    RecyclerView.Adapter<PictureGridAdapter.MyViewHolder>() {

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnLongClickListener, View.OnClickListener {
        override fun onLongClick(p0: View?): Boolean {
            listener.onItemLongClick(layoutPosition)
            return false
        }

        override fun onClick(clickedView: View?) {
            listener.onItemClick(layoutPosition)
        }

        val imageView: ImageView = v.findViewById(R.id.grid_image)
        val catName: TextView = v.findViewById(R.id.tv_cat_name)
        init {
            v.setOnLongClickListener(this)
            v.setOnClickListener(this)
         }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val root: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_grid_image, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        item.pictureUrl?.let{
            Glide.with(holder.imageView).load(it).into(holder.imageView).clearOnDetach()
        }
        holder.catName.text = item.name
    }

     interface ItemClickListener {
        fun onItemLongClick(position: Int)
        fun onItemClick(position: Int)
    }
}