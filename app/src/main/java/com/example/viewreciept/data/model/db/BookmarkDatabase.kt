package com.example.viewreciept.data.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.viewreciept.data.model.dao.BookmarkDao
import com.example.viewreciept.data.model.entity.Bookmark

@Database(entities = [Bookmark::class], version = 2, exportSchema = false)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: BookmarkDatabase? = null

        fun getInstance(context: Context): BookmarkDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkDatabase::class.java,
                    "bookmark_database"
                )
                    .fallbackToDestructiveMigration() // Clears the database on schema change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}