package com.example.viewreciept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.viewreciept.databinding.FragmentRegisterBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FirebaseAuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val email = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(requireContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val (isValid, validationMessage) = PasswordValidator.validate(password)
            if (!isValid) {
                Toast.makeText(requireContext(), validationMessage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.register(email, password) { isSuccess, message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                if (isSuccess) {
                    (requireActivity() as AuthenticationActivity).navigateToLoginFragment()
                }
            }
        }

        binding.goToLoginButton.setOnClickListener {
            (requireActivity() as AuthenticationActivity).navigateToLoginFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}