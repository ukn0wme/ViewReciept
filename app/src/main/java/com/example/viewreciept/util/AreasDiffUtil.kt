package com.example.viewreciept.util

import androidx.recyclerview.widget.DiffUtil
import com.example.viewreciept.data.model.entity.Area

class AreasDiffUtil : DiffUtil.ItemCallback<Area>(){
    override fun areItemsTheSame(oldItem: Area, newItem: Area): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Area, newItem: Area): Boolean {
        return oldItem == newItem
    }
}