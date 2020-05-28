package com.netlify.anshulgupta.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.netlify.anshulgupta.marsrealestate.databinding.GridViewItemBinding
import com.netlify.anshulgupta.marsrealestate.network.MarsProperty

class PhotoGridAdapter :
    ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

    class MarsPropertyViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty){
            binding.property = marsProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}