package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager()

    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.login_button)

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.pass_button)

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_account)


        findViewById<TextView>(R.id.Register_now).setOnClickListener {
            Log.d("MainActivity", "Pressed register label")
            val registerNow = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(registerNow)
            finish()
        }


        findViewById<Button>(R.id.next_button).setOnClickListener {

            if (credentialsManager.isEmailValid(email) && credentialsManager.isPasswordValid(password)) {

                Log.d("MainActivity", "Valid credentials")

                val nextIntent = Intent(this@LoginActivity, NewActivity::class.java) // Example next activity
                startActivity(nextIntent)
                finish()
            } else {

                if (!credentialsManager.isEmailValid(email)) {
                    emailInputLayout.error = "Invalid email format"
                } else {
                    emailInputLayout.error = null
                }

                if (!credentialsManager.isPasswordValid(password)) {
                    passwordInputLayout.error = "Password must have at least 8 chars, 1 uppercase, 1 number, and 1 symbol"
                } else {
                    passwordInputLayout.error = null
                }
            }
        }
    }
}