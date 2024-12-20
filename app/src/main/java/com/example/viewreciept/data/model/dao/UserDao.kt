package com.example.viewreciept.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.viewreciept.data.model.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_profile LIMIT 1")
    fun getUserProfile(): LiveData<User?>

    @Insert
    suspend fun insertUserProfile(profile: User)

    @Update
    suspend fun updateUserProfile(profile: User)
}