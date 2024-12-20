package com.example.viewreciept.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.viewreciept.data.model.entity.Meal
import com.example.viewreciept.data.repository.MealsRepository
import kotlinx.coroutines.launch

class MealsByAreaViewModel(private val repository: MealsRepository) : ViewModel() {

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> get() = _meals

    fun getMealsByArea(area: String) {
        viewModelScope.launch {
            val result = repository.getMealsByArea(area)
            _meals.postValue(result)
        }
    }
}

class MealsByAreaViewModelFactory(private val repository: MealsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealsByAreaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealsByAreaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}