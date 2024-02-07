package com.example.test8.presentation.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test8.databinding.ItemLayoutBinding
import com.example.test8.presentation.extension.loadImage
import com.example.test8.presentation.model.Item

class HomeFragmentPagerAdapter :
    ListAdapter<Item, HomeFragmentPagerAdapter.ItemsViewHolder>(ItemsDiffUtil()) {

    inner class ItemsViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Item

        fun bind() {
            model = currentList[adapterPosition]

            binding.apply {
                image.loadImage(model.cover)
                location.text = model.location
                reactCount.text = model.reactionCount.toString()
                title.text = model.title
                price.text = model.price
                ratingStars.rating = model.rate?.toFloat() ?: 0.0f
            }
        }
    }

    class ItemsDiffUtil : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemsViewHolder(
        ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind()
    }
}