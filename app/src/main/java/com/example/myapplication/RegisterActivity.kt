package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.com.example.myapplication.CredentialsManager
import com.google.android.material.textfield.TextInputEditText


class RegisterActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fullNameEditText = findViewById<TextInputEditText>(R.id.textInputEditFullName)
        val emailEditText = findViewById<TextInputEditText>(R.id.textInputEditValidEmail)
        val phoneEditText = findViewById<TextInputEditText>(R.id.textInputEditPhoneNum)
        val passwordEditText = findViewById<TextInputEditText>(R.id.textInputEditStrongPasswrd)

        // Set up the button click listener to navigate to UnnecessaryYet activity
        val button = findViewById<Button>(R.id.buttonNext)
        button.setOnClickListener {
            val intent = Intent(this, UnnecessaryYet::class.java)
            startActivity(intent)
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val fullName = fullNameEditText.text.toString()
            val phone = phoneEditText.text.toString()

            // Validate input
            if (email.isBlank() || password.isBlank() || fullName.isBlank() || phone.isBlank()) {
                // Show an error message if any field is empty
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!credentialsManager.isEmailValid(email)) {
                // Show an error message if the email is invalid
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Attempt to register using CredentialsManager
            val isRegistered = credentialsManager.register(fullName, email, phone, password)
            if (isRegistered) {
                // Registration successful: Navigate to Login screen
                Toast.makeText(this, "Registration successful! Please log in.", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Close the RegisterActivity after successful registration
            } else {
                // Registration failed (e.g., email already exists)
                Toast.makeText(this, "Email is already registered", Toast.LENGTH_SHORT).show()
            }
        }
        // Set up the clickable "Log in" part of the TextView
        val newMemberTextView =
            findViewById<TextView>(R.id.alreadyAMember)  // Adjust with your actual TextView ID
        val fullText = "Already a member? Log in"
        val spannableString = SpannableString(fullText)
        // Color and make "Log in" clickable
        val registerNowColor =
            ContextCompat.getColor(this, R.color.blue)
        val startIndex = fullText.indexOf("Log in")
        val endIndex = startIndex + "Log in".length
        // Apply color span
        spannableString.setSpan(
            ForegroundColorSpan(registerNowColor),
            startIndex,
            endIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        // Apply clickable span
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Navigate to RegisterActivity on click
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }, startIndex, endIndex, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        // Set the spannable text to the TextView and enable link movement
        newMemberTextView.text = spannableString
        newMemberTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}