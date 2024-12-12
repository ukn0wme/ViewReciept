package com.example.viewreciept.util

object PasswordValidator {

    fun validate(password: String): Pair<Boolean, String> {
        if (password.length < 8) {
            return Pair(false, "Password must be at least 8 characters long.")
        }
        if (!password.any { it.isUpperCase() }) {
            return Pair(false, "Password must contain at least one uppercase letter.")
        }
        if (!password.any { it.isLowerCase() }) {
            return Pair(false, "Password must contain at least one lowercase letter.")
        }
        if (!password.any { it.isDigit() }) {
            return Pair(false, "Password must contain at least one number.")
        }
        if (!password.any { "!@#\$%^&*()-_=+[{]}|;:'\",<.>/?".contains(it) }) {
            return Pair(false, "Password must contain at least one special character.")
        }
        return Pair(true, "Password is valid.")
    }
}