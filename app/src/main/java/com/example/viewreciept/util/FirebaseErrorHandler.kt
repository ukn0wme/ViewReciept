package com.example.viewreciept.util

object FirebaseErrorHandler {

    fun getErrorMessage(exception: Exception?): String {
        return when {
            exception?.message?.contains("email address is badly formatted") == true ->
                "The email address format is invalid. Please enter a valid email."

            exception?.message?.contains("email address is already in use") == true ->
                "This email is already registered. Please use a different email or log in."

            exception?.message?.contains("The password is invalid") == true ->
                "Incorrect password. Please try again."

            exception?.message?.contains("There is no user record") == true ->
                "No account found for this email. Please register or check your email address."

            exception?.message?.contains("network error") == true ->
                "Network error. Please check your internet connection and try again."

            exception?.message?.contains("WEAK_PASSWORD") == true ->
                "The password is too weak. Please choose a stronger password."

            else -> exception?.message ?: "An unknown error occurred. Please try again."
        }
    }
}