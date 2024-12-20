package com.example.viewreciept.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.viewreciept.data.model.entity.Area
import com.example.viewreciept.data.repository.MealsRepository
import kotlinx.coroutines.launch

class AreasViewModel(private val repository: MealsRepository) : ViewModel() {

    private val _areas = MutableLiveData<List<Area>>()
    val areas: LiveData<List<Area>> get() = _areas

    fun getAreas() {
        viewModelScope.launch {
            val result = repository.getAreas()
            _areas.postValue(result)
        }
    }
}

class AreasViewModelFactory(private val repository: MealsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AreasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AreasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}