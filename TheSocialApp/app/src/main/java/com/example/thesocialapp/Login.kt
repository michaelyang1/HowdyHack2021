package com.example.thesocialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thesocialapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerLink.setOnClickListener {
            intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            performLogin()
        }

    }

    private fun performLogin() {
        val email = binding.emailLogin.text.toString()
        val password = binding.passwordLogin.text.toString()

        // check for empty user inputs
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        // Firebase authentication to create new user with email and password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) return@addOnCompleteListener
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to sign in user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}