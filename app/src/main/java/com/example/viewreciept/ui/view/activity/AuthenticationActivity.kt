package com.example.viewreciept.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.viewreciept.ui.view.fragment.LoginFragment
import com.example.viewreciept.R
import com.example.viewreciept.ui.view.fragment.RegistrationFragment
import com.example.viewreciept.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the LoginFragment by default
        loadFragment(LoginFragment())
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
}