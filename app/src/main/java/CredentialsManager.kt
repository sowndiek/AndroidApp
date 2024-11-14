package com.example.yourapp

class CredentialsManager {
    private val emailPattern =
        "^[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}\\.[a-zA-Z]{2,6}$"

    fun isEmailValid(mail: String): Boolean {
        val regex = Regex(emailPattern)
        return regex.matches(mail)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}

