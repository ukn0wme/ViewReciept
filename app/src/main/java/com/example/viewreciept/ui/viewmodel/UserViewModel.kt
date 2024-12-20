package com.example.viewreciept.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.viewreciept.data.model.db.UserDatabase
import com.example.viewreciept.data.model.entity.User
import com.example.viewreciept.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = UserDatabase.getDatabase(application).userDao()
    private val repository = UserRepository(userDao)

    val user: LiveData<User?> = repository.getUserProfile()

    fun saveUser(profile: User) {
        viewModelScope.launch {
            repository.saveUserProfile(profile)
        }
    }
}