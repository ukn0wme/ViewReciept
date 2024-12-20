package com.example.viewreciept.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.viewreciept.data.model.entity.Bookmark
import com.example.viewreciept.data.repository.BookmarkRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application, private val bookmarkRepository: BookmarkRepository) : AndroidViewModel(application) {
    val bookmarkItems: LiveData<List<Bookmark>> = bookmarkRepository.getAllBookmarks()

    fun addBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            bookmarkRepository.addBookmark(bookmark)
        }
    }

    fun removeBookmark(mealId: String) {
        viewModelScope.launch {
            bookmarkRepository.removeBookmark(mealId)
        }
    }
}


class BookmarkViewModelFactory(private val application: Application, private val bookmarkRepository: BookmarkRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookmarkViewModel(application, bookmarkRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}