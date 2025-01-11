package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager.getInstance()

    private val fullNameInputLayout: TextInputLayout
        get() = findViewById(R.id.full_name)

    private val fullName: String
        get() = fullNameInputLayout.editText?.text?.toString().orEmpty()

    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.valid_email)

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val phoneNumberInputLayout: TextInputLayout
        get() = findViewById(R.id.phone_number)

    private val phoneNumber: String
        get() = phoneNumberInputLayout.editText?.text?.toString().orEmpty()

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.strong_pass)

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)
        findViewById<TextView>(R.id.login).setOnClickListener {
            Log.d("RegisterActivity", "Pressed login label")
            navigateToLogin()
        }
        findViewById<Button>(R.id.next_button).setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        var isValid = true
        if (fullName.isEmpty()) {
            fullNameInputLayout.error = "Full name is required"
            isValid = false
        } else {
            fullNameInputLayout.error = null
        }
        if (!credentialsManager.isEmailValid(email)) {
            emailInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            emailInputLayout.error = null
        }
        if (phoneNumber.isEmpty() || phoneNumber.length < 10) {
            phoneNumberInputLayout.error = "Phone number must have at least 10 digits"
            isValid = false
        } else {
            phoneNumberInputLayout.error = null
        }
        if (!credentialsManager.isPasswordValid(password)) {
            passwordInputLayout.error = "Password must have at least 8 chars, 1 uppercase, 1 number, and 1 symbol"
            isValid = false
        } else {
            passwordInputLayout.error = null
        }
        if (isValid) {
            val registrationSuccess = credentialsManager.register(fullName, email, phoneNumber, password)
            if (registrationSuccess) {
                Log.d("RegisterActivity", "User registered successfully")
                navigateToLogin()
            } else {
                emailInputLayout.error = "Email is already registered"
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}



