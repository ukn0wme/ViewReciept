package com.example.viewreciept.ui.view.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.viewreciept.data.network.ApiClient
import com.example.viewreciept.data.repository.MealsRepository
import com.example.viewreciept.databinding.FragmentMealsByAreaBinding
import com.example.viewreciept.ui.adapter.MealsAdapter
import com.example.viewreciept.ui.viewmodel.MealsByAreaViewModel
import com.example.viewreciept.ui.viewmodel.MealsByAreaViewModelFactory

class MealsByAreaFragment : Fragment() {
    private var _binding: FragmentMealsByAreaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MealsByAreaViewModel
    private val adapter: MealsAdapter by lazy {
        MealsAdapter()
    }

    companion object {
        fun newInstance(areaName: String) = MealsByAreaFragment().apply {
            arguments = Bundle().apply { putString("areaName", areaName) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsByAreaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val areaName = arguments?.getString("areaName") ?: return

        setupViewModel()
        setupRecyclerView()

        viewModel.getMealsByArea(areaName)

        viewModel.meals.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMealsByArea.adapter = adapter
    }

    private fun setupViewModel() {
        val repository = MealsRepository(ApiClient.apiService)
        viewModel = ViewModelProvider(
            this,
            MealsByAreaViewModelFactory(repository)
        ).get(MealsByAreaViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}