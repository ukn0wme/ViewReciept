package com.example.viewreciept.data.repository

import android.util.Log
import com.example.viewreciept.data.model.entity.Area
import com.example.viewreciept.data.model.entity.Meal
import com.example.viewreciept.data.network.ApiServiceInterface

class MealsRepository(private val apiService: ApiServiceInterface) {

    suspend fun getMealsByName(mealName: String): List<Meal> {
        return try {
            val response = apiService.searchByName(mealName)
            if (response.isSuccessful) {
                response.body()?.meals ?: emptyList()
            } else {
                Log.e("MealsRepository", "API Error: ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("MealsRepository", "Exception: ${e.localizedMessage}")
            emptyList()
        }
    }

    suspend fun getMealById(id: String): Meal?{
        val response = apiService.searchById(id)
        if(response.isSuccessful){
            response.body()?.let { mealsResponse ->
                Log.d("MealsRepository", "API Response: $mealsResponse")
                return mealsResponse.meals.firstOrNull() // Extract the first meal
            }
        }
        else {
            Log.e("MealsRepository", "Failed to fetch meal: ${response.errorBody()?.string()}")
        }
        return null
    }

    suspend fun getAreas(): List<Area> {
        val response = apiService.listAreas()
        if (response.isSuccessful) {
            return response.body()?.areas ?: emptyList()
        }
        return emptyList()
    }

    suspend fun getMealsByArea(area: String): List<Meal> {
        val response = apiService.filterByArea(area)
        if (response.isSuccessful) {
            return response.body()?.meals ?: emptyList()
        }
        return emptyList()
    }
}
