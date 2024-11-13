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


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Set window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Set up the button click listener to navigate to Create_Account2 activity
        val button = findViewById<Button>(R.id.buttonNext)
        button.setOnClickListener {
            val intent = Intent(this, Create_Account2::class.java)
            startActivity(intent)
        }
        // Set up the clickable "Register now" part of the TextView
        val newMemberTextView =
            findViewById<TextView>(R.id.newMember)  // Adjust with your actual TextView ID
        val fullText = "New Member? Register now"
        val spannableString = SpannableString(fullText)
        // Color and make "Register now" clickable
        val registerNowColor =
            ContextCompat.getColor(this, R.color.blue)
        val startIndex = fullText.indexOf("Register now")
        val endIndex = startIndex + "Register now".length
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
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }, startIndex, endIndex, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        // Set the spannable text to the TextView and enable link movement
        newMemberTextView.text = spannableString
        newMemberTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}
