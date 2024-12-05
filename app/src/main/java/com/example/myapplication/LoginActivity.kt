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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.com.example.myapplication.CredentialsManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager()

    // Properties for views using custom getters
    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.emailTextInputLayout)

    private val emailEditText: TextInputEditText
        get() = findViewById(R.id.emailEditText)

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.passwordTextInputLayout)

    private val passwordEditText: TextInputEditText
        get() = findViewById(R.id.passwordEditText)

    private val nextButton: Button
        get() = findViewById(R.id.buttonNext)

    private val newMemberTextView: TextView
        get() = findViewById(R.id.newMember)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set up edge-to-edge layout
        setupEdgeToEdgeLayout()

        // Set up the button click listener to handle login
        nextButton.setOnClickListener {
            validateAndLogin()
        }

        // Set up the "Register now" clickable span
        setupRegisterNowTextView()
    }

    private fun setupEdgeToEdgeLayout() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun validateAndLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        var isValid = true

        // Validate email
        if (!credentialsManager.isEmailValid(email)) {
            emailInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            emailInputLayout.error = null
        }

        // Validate password
        if (!credentialsManager.isPasswordValid(password)) {
            passwordInputLayout.error = "Password cannot be empty"
            isValid = false
        } else {
            passwordInputLayout.error = null
        }

        // Check hardcoded credentials if validation passed
        if (isValid && credentialsManager.areCredentialsValid(email, password)) {
            emailInputLayout.error = null
            passwordInputLayout.error = null
            navigateToCreateAccount2()
        } else if (isValid) {
            passwordInputLayout.error = "Invalid credentials"
        }
    }

    private fun navigateToCreateAccount2() {
        val intent = Intent(this, UnnecessaryYet::class.java)
        startActivity(intent)
        finish() // Optional: Close this activity
    }

    private fun setupRegisterNowTextView() {
        val fullText = "New Member? Register now"
        val spannableString = SpannableString(fullText)

        val registerNowColor = ContextCompat.getColor(this, R.color.blue)
        val startIndex = fullText.indexOf("Register now")
        val endIndex = startIndex + "Register now".length

        // Apply color span
        spannableString.setSpan(
            ForegroundColorSpan(registerNowColor),
            startIndex,
            endIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Make "Register now" clickable and handle click
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }, startIndex, endIndex, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        // Set the spannable text to the TextView and enable link movement
        newMemberTextView.text = spannableString
        newMemberTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}
