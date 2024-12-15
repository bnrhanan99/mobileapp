package com.example.mobileapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class SampleActivity : AppCompatActivity(R.layout.activity_sample), LoginFragment.EventInterface,
    RegisterFragment.EventInterface {
    var credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("INSTANCES","Activity instance:$credentialsManager")
        supportFragmentManager.commit {
            val fragment = LoginFragment()
            fragment.credentialsManager = credentialsManager
            replace(R.id.fragment_container_view, fragment)
        }
    }

    override fun onRegisterPressed() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, RegisterFragment().apply {
                credentialsManager = this@SampleActivity.credentialsManager
            })
            addToBackStack(null)
        }
    }

    override fun onLoginPressed() {
        supportFragmentManager.commit {
            replace<LoginFragment>(R.id.fragment_container_view).apply {
                credentialsManager = this@SampleActivity.credentialsManager
            }
            addToBackStack(null)
        }
    }
}
