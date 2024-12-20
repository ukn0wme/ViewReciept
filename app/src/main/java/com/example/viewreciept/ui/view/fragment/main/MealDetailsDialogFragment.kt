package com.example.viewreciept.ui.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.viewreciept.R
import com.example.viewreciept.data.model.entity.Bookmark
import com.example.viewreciept.data.model.entity.Meal
import com.example.viewreciept.data.network.ApiClient
import com.example.viewreciept.data.repository.BookmarkRepository
import com.example.viewreciept.data.repository.MealsRepository
import com.example.viewreciept.databinding.FragmentMealDetailsDialogBinding
import com.example.viewreciept.ui.viewmodel.MealViewModel
import com.example.viewreciept.ui.viewmodel.MealViewModelFactory

class MealDetailsDialogFragment : DialogFragment() {
    private var _binding: FragmentMealDetailsDialogBinding? = null
    private val binding get() = _binding!!
    private var mealId: String? = null
    private lateinit var viewModel: MealViewModel
    private var meal: Meal? = null

    companion object {
        const val ARG_MEAL_ID = "meal_id"

        fun newInstance(mealId: String): MealDetailsDialogFragment {
            val fragment = MealDetailsDialogFragment()
            val args = Bundle()
            args.putString(ARG_MEAL_ID, mealId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealId = arguments?.getString(ARG_MEAL_ID)
        setStyle(STYLE_NO_FRAME, R.style.FadeDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeMealDetails()

        mealId?.let { id ->
            viewModel.loadMealDetails(id)
        }

        //Maksat
        binding.addToCartButton.setOnClickListener {
            meal?.let { meal ->
                val bookmark = Bookmark(
                    mealId = meal.idMeal,
                    mealName = meal.strMeal,
                    mealArea = meal.strArea,
                    mealCategory = meal.strCategory,
                    mealThumb = meal.strMealThumb,
                    strInstructions = meal.strInstructions
                )
                viewModel.addToBookmark(bookmark)
                Toast.makeText(context, "${meal.strMeal} added to bookmarks", Toast.LENGTH_SHORT)
                    .show()
            } ?: run {
                Toast.makeText(context, "Error: No meal found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViewModel() {
        val repository = MealsRepository(ApiClient.apiService)
        val bookmarkRepository = BookmarkRepository(requireContext())
        val factory = MealViewModelFactory(repository, bookmarkRepository)
        viewModel = ViewModelProvider(this, factory).get(MealViewModel::class.java)
    }

    private fun updateUI(meal: Meal) {
        binding.mealName.text = meal.strMeal
        binding.mealDescription.text = meal.strInstructions
        binding.categoryArea.text = meal.strArea
        Glide.with(this)
            .load(meal.strMealThumb)
            .into(binding.mealImage)
    }

    private fun observeMealDetails() {
        viewModel.mealDetails.observe(viewLifecycleOwner, Observer { mealDetails ->
            mealDetails?.let {
                meal = it
                updateUI(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setWindowAnimations(R.style.FadeDialogTheme)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_fragment_card)
    }
}