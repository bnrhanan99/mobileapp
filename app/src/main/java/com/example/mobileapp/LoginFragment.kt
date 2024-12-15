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

class LoginFragment : Fragment() {
    public var credentialsManager: CredentialsManager? = null
    private var listener: EventInterface? = null
    interface EventInterface {
        fun onRegisterPressed()

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
        return inflater.inflate(R.layout.login_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerNowTextView: TextView = view.findViewById(R.id.Register_now)
        registerNowTextView.setOnClickListener {
            Log.d("LoginFragment", "Pressed register now label")
            listener?.onRegisterPressed()
            Toast.makeText(context, "Navigating to Register Fragment", Toast.LENGTH_SHORT).show()
        }
    }
}

