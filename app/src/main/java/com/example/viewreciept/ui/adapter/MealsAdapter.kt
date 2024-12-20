package com.example.viewreciept.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewreciept.data.model.entity.Meal
import com.example.viewreciept.databinding.ItemMealBinding
import com.example.viewreciept.ui.view.fragment.main.MealDetailsDialogFragment
import com.example.viewreciept.util.MealsListDiffUtil

class MealsAdapter : ListAdapter<Meal, MealsAdapter.ViewHolder>(MealsListDiffUtil()) {
    inner class ViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedMeal = getItem(position)
                    val mealId = selectedMeal.idMeal
                    val fragmentManager = (itemView.context as FragmentActivity).supportFragmentManager

                    MealDetailsDialogFragment.newInstance(mealId)
                        .show(fragmentManager, "MealDetailsDialogFragment")
                }
            }
        }

        fun bind(meal: Meal) {
            binding.mealName.text = meal.strMeal
            binding.mealCategory.text = meal.strCategory
            binding.mealArea.text = meal.strArea
            Glide.with(itemView.context)
                .load(meal.strMealThumb)
                .into(binding.mealImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = getItem(position)
        if (meal != null) {
            holder.bind(meal)
        } else {
            Log.e("MealsAdapter", "Meal is null at position: $position")
        }
    }
}