package com.example.myapplication

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CredentialsManagerTest {
    private lateinit var credentialsManager: CredentialsManager

    @Before
    fun setUp() {
        // This method is called before every test
        credentialsManager = CredentialsManager()
    }

    // Test for an empty email string
    @Test
    fun givenEmptyEmailThenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = ""

        val result = credentialsManager.isEmailValid(email)
        assertFalse(result)
    }

    @Test
    fun givenInvalidEmailFormatThenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = "invalid-email"

        val result = credentialsManager.isEmailValid(email)
        assertFalse(result)
    }

    // Test for a well-formatted email
    @Test
    fun givenValidEmailThenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val email = "test@example.com"
        val result = credentialsManager.isEmailValid(email)
        assertTrue(result)
    }

    // Test for an empty password string
    @Test
    fun givenEmptyPasswordThenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val password = ""
        val result = credentialsManager.isPasswordValid(password)
        assertFalse(result)
    }

    // Test for a filled password string
    @Test
    fun givenNonEmptyPasswordThenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val password = "password123"
        val result = credentialsManager.isPasswordValid(password)
        assertTrue(result)
    }
}



