package com.example.viewreciept

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(
        email: String,
        password: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, "Registration successful!")
                } else {
                    val errorMessage = FirebaseErrorHandler.getErrorMessage(task.exception)
                    onComplete(false, errorMessage)
                }
            }
    }

    fun login(
        email: String,
        password: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, "Login successful!")
                } else {
                    val errorMessage = FirebaseErrorHandler.getErrorMessage(task.exception)
                    onComplete(false, errorMessage)
                }
            }
    }
}