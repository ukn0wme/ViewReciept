package com.example.viewreciept.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.viewreciept.ui.viewmodel.FirebaseAuthViewModel
import com.example.viewreciept.databinding.FragmentLoginBinding
import com.example.viewreciept.ui.view.activity.AuthenticationActivity

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FirebaseAuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(requireContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(email, password) { isSuccess, message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                if (isSuccess) {
                    // Navigate to dashboard or home screen
                }
            }
        }

        binding.goToRegisterButton.setOnClickListener {
            (requireActivity() as AuthenticationActivity).navigateToRegistrationFragment()
        }

        val isRegistrationSuccess = arguments?.getBoolean("registration_success") ?: false
        if (isRegistrationSuccess) {
            Toast.makeText(requireContext(), "Registration successful! Please log in.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}