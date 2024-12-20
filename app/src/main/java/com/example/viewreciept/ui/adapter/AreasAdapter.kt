package com.example.viewreciept.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewreciept.R
import com.example.viewreciept.data.model.entity.Area
import com.example.viewreciept.databinding.ItemAreaBinding
import com.example.viewreciept.util.AreasDiffUtil

class AreasAdapter(private val onClick: (Area) -> Unit) :
    ListAdapter<Area, AreasAdapter.ViewHolder>(AreasDiffUtil()) {

    inner class ViewHolder(private val binding: ItemAreaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(area: Area) {
            binding.areaName.text = area.name
            binding.root.setOnClickListener { onClick(area) }
            loadFlagImage(area.name, binding.areaImg)
        }

        private fun loadFlagImage(areaName: String, imageView: ImageView) {
            // Map area names to flag resource names
            val flagResId = when (areaName.lowercase()) {
                "american" -> R.drawable.icon_usa
                "british" -> R.drawable.icon_uk
                "canadian" -> R.drawable.icon_canada
                "chinese" -> R.drawable.icon_china
                "croatian" -> R.drawable.icon_croatian
                "dutch" -> R.drawable.icon_netherlands
                "egyptian" -> R.drawable.icon_egypt
                "filipino" -> R.drawable.icon_filipino
                "french" -> R.drawable.icon_france
                "greek" -> R.drawable.icon_greece
                "indian" -> R.drawable.icon_india
                "irish" -> R.drawable.icon_ireland
                "italian" -> R.drawable.icon_italy
                "jamaican" -> R.drawable.icon_yamaica
                "japanese" -> R.drawable.icon_japan
                "kenyan" -> R.drawable.icon_kenya
                "malaysian" -> R.drawable.icon_malaysia
                "mexican" -> R.drawable.icon_mexico
                "moroccan" -> R.drawable.icon_morocco
                "polish" -> R.drawable.icon_poland
                "portuguese" -> R.drawable.icon_portugal
                "russian" -> R.drawable.icon_russia
                "spanish" -> R.drawable.icon_spain
                "thai" -> R.drawable.icon_thailand
                "tunisian" -> R.drawable.icon_tunis
                "turkish" -> R.drawable.icon_turkey
                "ukrainian" -> R.drawable.icon_ukrain
                "unknown" -> R.drawable.icon_unknown
                "vietnamese" -> R.drawable.icon_vietnam
                else -> R.drawable.icon_unknown
            }
            imageView.setImageResource(flagResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}