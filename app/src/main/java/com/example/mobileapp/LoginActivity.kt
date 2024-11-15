package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_account)

        val registerNowTextView = findViewById<TextView>(R.id.Register_now)
        registerNowTextView.setOnClickListener {
            Log.d("LoginActivity", "Pressed register now label")

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
