package com.example.viewreciept.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "bookmarks")
data class Bookmark(
    @PrimaryKey val mealId: String,
    val mealName: String,
    val mealArea: String,
    val mealCategory: String,
    val mealThumb: String,
    val strInstructions: String
) : Serializable