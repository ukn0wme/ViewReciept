package com.example.viewreciept.ui.view.fragment.authentication

import android.content.Intent
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
import com.example.viewreciept.ui.view.activity.MainActivity

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
                    navigateToMainActivity()
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

    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Close AuthenticationActivity to prevent back navigation
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}