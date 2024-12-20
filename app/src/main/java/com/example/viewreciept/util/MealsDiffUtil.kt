package com.example.viewreciept.util

import androidx.recyclerview.widget.DiffUtil
import com.example.viewreciept.data.model.entity.Meal

class MealsListDiffUtil : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}