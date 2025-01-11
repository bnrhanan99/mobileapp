package com.example.mobileapp

class CredentialsManager {
    private val credentials = mutableMapOf<String, String>()

    private val emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"

    companion object {
        @Volatile private var instance: CredentialsManager? = null

        fun getInstance(): CredentialsManager {
            return instance ?: synchronized(this) {
                instance ?: CredentialsManager().also { instance = it }
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Regex(emailPattern).matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
        return Regex(passwordPattern).matches(password)
    }

    fun login(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        return credentials[normalizedEmail] == password
    }

    fun register(fullName: String, email: String, phoneNumber: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        println("Attempting to register: $normalizedEmail")
        println("Current stored emails: ${credentials.keys}")
        if (credentials.containsKey(normalizedEmail)) {
            println("Email already exists: $normalizedEmail")
            return false
        }
        credentials[normalizedEmail] = password
        println("Registering new email: $normalizedEmail")
        println("Current stored emails after registration: ${credentials.keys}")
        return true
    }
}

