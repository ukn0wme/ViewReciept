package com.example.viewreciept.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.viewreciept.ui.view.fragment.authentication.LoginFragment
import com.example.viewreciept.R
import com.example.viewreciept.ui.view.fragment.authentication.RegistrationFragment
import com.example.viewreciept.databinding.ActivityAuthenticationBinding
import com.google.firebase.auth.FirebaseAuth

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            // User is already logged in, navigate to MainActivity
            navigateToMainActivity()
        } else {
            // Load the LoginFragment by default
            loadFragment(LoginFragment())
        }
    }

    private fun loadFragment(fragment: Fragment, args: Bundle? = null) {
        if (args != null) {
            fragment.arguments = args
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    // Navigate to RegistrationFragment
    fun navigateToRegistrationFragment() {
        loadFragment(RegistrationFragment())
    }

    // Navigate to LoginFragment
    fun navigateToLoginFragment(isRegistrationSuccess: Boolean = false) {
        val args = Bundle().apply {
            putBoolean("registration_success", isRegistrationSuccess)
        }
        loadFragment(LoginFragment(), args)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close AuthenticationActivity
    }
}