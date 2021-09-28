package com.example.thesocialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.thesocialapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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

    /** Register the user using FirebaseAuth */
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
                if (it.isSuccessful) {
                    saveUserToFirebaseDatabase()
                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    /** Save user information into Firebase realtime database */
    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid ?: "" //elvis operator
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(
            uid,
            binding.fullNameRegister.text.toString(),
            binding.emailRegister.text.toString()
        )

        ref.setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Added user", Toast.LENGTH_SHORT).show()


                val intent = Intent(this, ActivityFeed::class.java)
                //clear off previous activities on activities stack
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to add user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

/** Class to hold key user information */
class User(val uid: String, val name: String, val email: String) { // put this into a module file
    constructor() : this("", "", "") // default no argument constructor
}