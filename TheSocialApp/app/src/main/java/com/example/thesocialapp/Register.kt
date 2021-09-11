package com.example.thesocialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.thesocialapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginLink.setOnClickListener {
            intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.buttonRegister.setOnClickListener {
            performRegister()
        }
    }

    private fun performRegister() {
        val email = binding.emailRegister.text.toString()
        val password = binding.passwordRegister.text.toString()

        // check for empty user inputs
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        // Firebase authentication to create new user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) return@addOnCompleteListener
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}