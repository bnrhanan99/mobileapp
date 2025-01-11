package com.example.mobileapp

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse

class CredentialsManagerTest {
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager.getInstance()
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager.getInstance()
        val isEmailValid = credentialsManager.isEmailValid("email@example.com")
        assertEquals(true, isEmailValid)
    }

    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager.getInstance()
        val isEmailValid = credentialsManager.isEmailValid("email@.st")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager.getInstance()
        assertEquals(false, credentialsManager.isPasswordValid(""))
    }

    @Test
    fun givenValidPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager.getInstance()
        assertEquals(true, credentialsManager.isPasswordValid("Str0ngP@ssword"))
    }

    @Test
    fun givenProperUnusedCredentials_whenUserRegisters_thenSucceed() {
        val credentialsManager = CredentialsManager.getInstance()
        val newEmail = "new@te.st"
        val newPassword = "1234qwer"
        val registrationSuccess = credentialsManager.register("Full Name", newEmail, "600 600 600", newPassword)
        assertTrue("Registration should succeed for a new email", registrationSuccess)
        val isLoginSuccess = credentialsManager.login(newEmail, newPassword)
        assertTrue("Login should succeed with the registered email", isLoginSuccess)
    }

    @Test
    fun givenTakenEmail_whenRegister_thenRegisterFails() {
        val credentialsManager = CredentialsManager.getInstance()
        val email = "taken@example.com"
        val password = "1234"
        val firstRegistration = credentialsManager.register("User 1", email, "1234567890", password)
        assertTrue("First registration should succeed", firstRegistration)
        val secondRegistration = credentialsManager.register("User 2", email, "9876543210", "0987")
        assertFalse("Registration with a taken email should fail", secondRegistration)

    }

    @Test
    fun givenEmailWithDifferentCases_whenRegister_thenFail() {
        val credentialsManager = CredentialsManager.getInstance()
        val email = "TEST@te.st"
        val password = "1234"
        val firstRegistration = credentialsManager.register("User", email, "1234567890", password)
        assertTrue("First registration should succeed", firstRegistration)
        val secondRegistration = credentialsManager.register("User2", "test@te.st", "9876543210", "5678")
        assertFalse("Registration with a duplicate email (case-insensitive) should fail", secondRegistration)
    }

    @Test
    fun givenEmailWithDifferentCases_whenLogin_thenSucceed() {
        val credentialsManager = CredentialsManager.getInstance()
        val email = "TEST@te.st"
        val password = "1234"
        credentialsManager.register("Full Name", email, "600 600 600", password)
        val loginSuccess = credentialsManager.login("test@te.st", password)
        assertTrue("Login should succeed with a case-insensitive email match", loginSuccess)
    }
}

