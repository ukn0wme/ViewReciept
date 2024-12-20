package com.example.viewreciept.ui.view.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.viewreciept.R
import com.example.viewreciept.data.network.ApiClient
import com.example.viewreciept.data.repository.MealsRepository
import com.example.viewreciept.databinding.FragmentAreasBinding
import com.example.viewreciept.ui.adapter.AreasAdapter
import com.example.viewreciept.ui.viewmodel.AreasViewModel
import com.example.viewreciept.ui.viewmodel.AreasViewModelFactory
import com.example.viewreciept.util.GridSpacingItemDecoration

class AreasFragment : Fragment() {
    private var _binding: FragmentAreasBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AreasViewModel
    private val adapter: AreasAdapter by lazy {
        AreasAdapter { area ->
            navigateToMealsByAreaFragment(area.name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()

        viewModel.areas.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.getAreas()
    }

    private fun setupRecyclerView() {
        val spanCount = 2 // Number of columns
        val spacing = 16 // Spacing between items in dp
        val includeEdge = true

        binding.areasRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // 2 columns
        binding.areasRecyclerView.adapter = adapter
        binding.areasRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(spanCount, spacing, includeEdge)
        )
    }

    private fun setupViewModel() {
        val repository = MealsRepository(ApiClient.apiService)
        viewModel = ViewModelProvider(this, AreasViewModelFactory(repository))
            .get(AreasViewModel::class.java)
    }

    private fun navigateToMealsByAreaFragment(areaName: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, MealsByAreaFragment.newInstance(areaName))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}