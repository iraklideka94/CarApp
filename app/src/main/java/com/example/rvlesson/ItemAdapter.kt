package com.example.rvlesson

import android.content.Context
import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rvlesson.databinding.ItemListBinding

class ItemAdapter(var context: Context, var itemClickListener: OnItemClickListener): ListAdapter<Item,ItemAdapter.ItemViewHolder>(ItemCallback()) {

    class ItemCallback: DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ItemViewHolder(val binding: ItemListBinding,var itemClickListener: OnItemClickListener):RecyclerView.ViewHolder(binding.root){
        init {
         binding.root.setOnLongClickListener { p0 ->
             itemClickListener.onItemLongClick(adapterPosition)
             return@setOnLongClickListener false
         }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemViewHolder(view,itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = currentList[position]

        Glide.with(context)
            .load(item.imageUrl)
            .into(holder.binding.imageView)

        holder.binding.textBrand.text = item.brand
        holder.binding.textmodel.text = item.model

    }
    interface OnItemClickListener {
        fun onItemLongClick(position: Int)
    }
}