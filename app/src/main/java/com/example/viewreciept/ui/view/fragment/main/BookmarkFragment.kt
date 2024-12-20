package com.example.viewreciept.ui.view.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewreciept.data.model.entity.Bookmark
import com.example.viewreciept.data.repository.BookmarkRepository
import com.example.viewreciept.databinding.FragmentBookmarkBinding
import com.example.viewreciept.ui.adapter.BookmarkAdapter
import com.example.viewreciept.ui.viewmodel.BookmarkViewModel
import com.example.viewreciept.ui.viewmodel.BookmarkViewModelFactory

class BookmarkFragment : Fragment(){
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private lateinit var viewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = BookmarkViewModelFactory(requireActivity().application, BookmarkRepository(requireContext()))
        viewModel = ViewModelProvider(this, factory).get(BookmarkViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        bookmarkAdapter = BookmarkAdapter(
            mutableListOf(),
            onItemClick = { bookmark ->
                openMealDetails(bookmark)
            },
            onDeleteClick = { bookmark ->
                viewModel.removeBookmark(bookmark.mealId)
            }
        )

        binding.bookmarkRecyclerView.apply {
            adapter = bookmarkAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun openMealDetails(bookmark: Bookmark) {
        val fragment = MealDetailsDialogFragment.newInstance(bookmark.mealId)
        fragment.show(parentFragmentManager, "MealDetailsDialogFragment")
    }

    private fun observeViewModel() {
        viewModel.bookmarkItems.observe(viewLifecycleOwner, Observer { items ->
            bookmarkAdapter.updateItems(items)
        })
    }
}