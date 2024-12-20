package com.example.viewreciept.ui.view.fragment.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewreciept.data.network.ApiClient
import com.example.viewreciept.data.repository.BookmarkRepository
import com.example.viewreciept.data.repository.MealsRepository
import com.example.viewreciept.databinding.FragmentMealSearchBinding
import com.example.viewreciept.ui.adapter.MealsAdapter
import com.example.viewreciept.ui.viewmodel.MealViewModel
import com.example.viewreciept.ui.viewmodel.MealViewModelFactory

class MealSearchFragment : Fragment() {

    private var _binding: FragmentMealSearchBinding? = null
    private val binding get() = _binding!!

    private val adapter: MealsAdapter by lazy {
        MealsAdapter()
    }

    private lateinit var viewModel: MealViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater, container, false)
        binding.recyclerViewMeals.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMeals.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupSearch()
    }

    private fun setupViewModel() {
        val repository =
            MealsRepository(ApiClient.apiService) // Ensure your ApiClient is correctly initialized
        val bookmarkRepository = BookmarkRepository(requireContext())
        val viewModelFactory = MealViewModelFactory(repository, bookmarkRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MealViewModel::class.java)

        viewModel.searchResults.observe(viewLifecycleOwner, Observer { meals ->
            if (meals.isNotEmpty()) {
                adapter.submitList(meals)
                Log.d("MealSearchFragment", "Meals displayed: $meals")
            } else {
                Log.d("MealSearchFragment", "No meals found")
                Toast.makeText(requireContext(), "No meals found", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupSearch() {
        binding.searchButton.setOnClickListener {
            val query = binding.searchEt.text.toString()
            if (query.isNotEmpty()) {
                Log.d("MealSearchFragment", "Searching for: $query")
                viewModel.searchMealsByName(query)
            } else {
                Toast.makeText(context, "Please enter a search term", Toast.LENGTH_SHORT).show()
            }
        }
    }

}