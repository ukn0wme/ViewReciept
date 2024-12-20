package com.example.viewreciept.data.repository

import androidx.lifecycle.LiveData
import com.example.viewreciept.data.model.dao.UserDao
import com.example.viewreciept.data.model.entity.User

class UserRepository(private val userDao: UserDao) {
    fun getUserProfile(): LiveData<User?> = userDao.getUserProfile()

    suspend fun saveUserProfile(profile: User) {
        if (profile.id == 0) {
            userDao.insertUserProfile(profile)
        } else {
            userDao.updateUserProfile(profile)
        }
    }
}