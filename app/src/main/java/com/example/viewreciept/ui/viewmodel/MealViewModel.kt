package com.example.viewreciept.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.viewreciept.data.model.entity.Bookmark
import com.example.viewreciept.data.model.entity.Meal
import com.example.viewreciept.data.repository.BookmarkRepository
import com.example.viewreciept.data.repository.MealsRepository
import kotlinx.coroutines.launch

class MealViewModel(private val repository: MealsRepository, private val bookmarkRepository: BookmarkRepository):
    ViewModel() {

    private val _searchResults = MutableLiveData<List<Meal>>()
    val searchResults: LiveData<List<Meal>> = _searchResults

    fun searchMealsByName(mealName: String) {
        viewModelScope.launch {
            val results = repository.getMealsByName(mealName)
            _searchResults.postValue(results)
        }
    }

    private val _mealDetails = MutableLiveData<Meal?>()
    val mealDetails: LiveData<Meal?> = _mealDetails

    fun loadMealDetails(id: String){
        viewModelScope.launch {
            val meal = repository.getMealById(id)
            if (meal == null) {
                Log.e("MealViewModel", "No meal found for ID: $id")
            }
            _mealDetails.postValue(meal)
        }
    }

    fun addToBookmark(bookmark: Bookmark){
        viewModelScope.launch {
            bookmarkRepository.addBookmark(bookmark)
        }
    }
}

class MealViewModelFactory(private val repository: MealsRepository, private val bookmarkRepository: BookmarkRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealViewModel(repository, bookmarkRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}