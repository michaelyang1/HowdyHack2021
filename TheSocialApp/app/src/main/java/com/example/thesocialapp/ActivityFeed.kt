package com.example.thesocialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ActivityFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseAuth.getInstance().signOut()

        // check if user is signed in
        if (!verifyUserIsLoggedIn()) {
            return
        }

        setContentView(R.layout.activity_feed)
    }

    private fun verifyUserIsLoggedIn(): Boolean {
        // check if user is logged into the app
        val uid = FirebaseAuth.getInstance().uid
        Log.e("UID", uid.toString())

        return if (uid == null) {
            val intent = Intent(this, Login::class.java)
            // intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            // finish()
            false
        } else {
            true
        }
    }
}