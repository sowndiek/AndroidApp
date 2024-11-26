package com.example.myapplication

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

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

    @Test
    fun givenUniqueEmail_whenRegister_thenAddAccount() {
        val credentialsManager = CredentialsManager()

        // Register a new account
        val result = credentialsManager.register(
            fullName = "John Doe",
            email = "unique@example.com",
            phone = "123456789",
            password = "securePassword123"
        )

        // Assert that the registration was successful
        assertEquals(true, result)
    }

    @Test
    fun givenDuplicateEmail_whenRegister_thenReturnError() {
        val credentialsManager = CredentialsManager()

        // Register the same email twice
        credentialsManager.register(
            fullName = "John Doe",
            email = "duplicate@example.com",
            phone = "123456789",
            password = "securePassword123"
        )
        val result = credentialsManager.register(
            fullName = "Jane Doe",
            email = "duplicate@example.com",
            phone = "987654321",
            password = "differentPassword123"
        )

        // Assert that the second registration failed
        assertEquals(false, result)
    }
}



