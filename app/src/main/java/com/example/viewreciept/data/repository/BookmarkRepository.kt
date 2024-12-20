package com.example.viewreciept.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.viewreciept.data.model.dao.BookmarkDao
import com.example.viewreciept.data.model.db.BookmarkDatabase
import com.example.viewreciept.data.model.entity.Bookmark

class BookmarkRepository(context: Context) {
    private val bookmarkDao: BookmarkDao

    init {
        val database = BookmarkDatabase.getInstance(context)
        bookmarkDao = database.bookmarkDao()
    }

    fun getAllBookmarks(): LiveData<List<Bookmark>> = bookmarkDao.getAllBookmarks()

    suspend fun addBookmark(bookmark: Bookmark) {
        bookmarkDao.insertBookmark(bookmark)
    }

    suspend fun removeBookmark(mealId: String) {
        bookmarkDao.deleteBookmarkById(mealId)
    }
}