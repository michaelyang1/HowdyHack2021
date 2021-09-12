package com.example.thesocialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.example.thesocialapp.databinding.ActivityFeedBinding

class ActivityFeed : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseAuth.getInstance().signOut()

        // check if user is signed in
        if (!verifyUserIsLoggedIn()) {
            return
        }

        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_feed, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_map) {
            intent = Intent(this, ActivityMap::class.java)
            startActivity(intent)
            return true;
        }
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut()
            intent = Intent(this, Login::class.java)
            startActivity(intent)
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}