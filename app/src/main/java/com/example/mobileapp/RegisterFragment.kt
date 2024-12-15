package com.example.mobileapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {
    public var credentialsManager: CredentialsManager? = null
    private var listener: EventInterface? = null
    interface EventInterface {
        fun onLoginPressed()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventInterface) {
            listener = context
        } else {
            throw ClassCastException("$context must implement EventInterface")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginTextView: TextView = view.findViewById(R.id.login)
        loginTextView.setOnClickListener {
            Log.d("LoginFragment", "Pressed register now label")
            listener?.onLoginPressed()
            Toast.makeText(context, "Navigating to Register Fragment", Toast.LENGTH_SHORT).show()
        }
    }
}