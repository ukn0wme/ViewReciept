package com.example.viewreciept.ui.view.fragment.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.viewreciept.data.model.entity.User
import com.example.viewreciept.databinding.FragmentProfileBinding
import com.example.viewreciept.ui.view.activity.AuthenticationActivity
import com.example.viewreciept.ui.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import java.io.File

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private lateinit var viewModel: UserViewModel
    private var currentProfile: User? = null

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val imagePath = saveImageToInternalStorage(it)
                currentProfile = currentProfile?.copy(profilePicturePath = imagePath)
                    ?: User(
                        id = 0,
                        name = "",
                        surname = "",
                        favoriteKitchen = "",
                        favoriteMeal = "",
                        profilePicturePath = imagePath
                    )
                binding.profilePicture.setImageURI(uri)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Display user email
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            binding.userEmail.text = "Logged in as: ${currentUser.email}"
        } else {
            binding.userEmail.text = "No user logged in"
        }

        // Logout button action
        binding.logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            navigateToAuthenticationActivity()
        }

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        observeViewModel()

        binding.saveButton.setOnClickListener {
            saveProfile()
        }

        binding.profilePicture.setOnClickListener {
            pickImage()
        }
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) { profile ->
            profile?.let {
                currentProfile = it
                populateFields(it)
            }
        }
    }

    private fun populateFields(profile: User) {
        binding.nameEditText.setText(profile.name)
        binding.surnameEditText.setText(profile.surname)
        binding.favoriteKitchenEditText.setText(profile.favoriteKitchen)
        binding.favoriteMealEditText.setText(profile.favoriteMeal)
        binding.profilePicture.setImageURI(Uri.parse(profile.profilePicturePath))
    }

    private fun saveProfile() {
        val name = binding.nameEditText.text.toString()
        val surname = binding.surnameEditText.text.toString()
        val favoriteKitchen = binding.favoriteKitchenEditText.text.toString()
        val favoriteMeal = binding.favoriteMealEditText.text.toString()
        val profilePicturePath = currentProfile?.profilePicturePath ?: ""

        val newProfile = User(
            id = currentProfile?.id ?: 0,
            name = name,
            surname = surname,
            favoriteKitchen = favoriteKitchen,
            favoriteMeal = favoriteMeal,
            profilePicturePath = profilePicturePath
        )

        viewModel.saveUser(newProfile)
        Toast.makeText(requireContext(), "Profile saved!", Toast.LENGTH_SHORT).show()
    }

    private fun pickImage() {
        // Launch the image picker
        pickImageLauncher.launch("image/*")
    }

    private fun saveImageToInternalStorage(uri: Uri): String {
        val context = requireContext()
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileName = "profile_picture_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return file.absolutePath
    }
    private fun navigateToAuthenticationActivity() {
        val intent = Intent(requireContext(), AuthenticationActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Close MainActivity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}