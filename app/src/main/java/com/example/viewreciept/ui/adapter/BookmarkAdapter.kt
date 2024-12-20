package com.example.viewreciept.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewreciept.data.model.entity.Bookmark
import com.example.viewreciept.databinding.ItemBookmarkBinding

class BookmarkAdapter(
    private var bookmarkItems: MutableList<Bookmark>,
    private val onDeleteClick: (Bookmark) -> Unit,
    private val onItemClick: (Bookmark) -> Unit
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    inner class BookmarkViewHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bookmarkItem: Bookmark) {
            binding.mealName.text = bookmarkItem.mealName
            binding.mealArea.text = bookmarkItem.mealArea
            binding.mealCategory.text = bookmarkItem.mealCategory
            Glide.with(itemView.context)
                .load(bookmarkItem.mealThumb)
                .into(binding.mealImage)

            // Handle click to view details
            binding.root.setOnClickListener {
                onItemClick(bookmarkItem)
            }

            // Handle delete button
            binding.deleteButton.setOnClickListener {
                onDeleteClick(bookmarkItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(bookmarkItems[position])
    }

    override fun getItemCount(): Int = bookmarkItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<Bookmark>) {
        bookmarkItems = newItems.toMutableList()
        notifyDataSetChanged()
    }
}