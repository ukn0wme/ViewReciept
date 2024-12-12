package com.example.viewreciept

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
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