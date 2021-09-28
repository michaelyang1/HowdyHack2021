package com.example.thesocialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.example.thesocialapp.databinding.ActivityFeedBinding

class ActivityFeed : AppCompatActivity() {
    private lateinit var contacts: ArrayList<Contact>
    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check if user is signed in
        if (!verifyUserIsLoggedIn()) {
            return
        }

        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ...
        // Lookup the recyclerview in activity layout
        val rvContacts = findViewById<View>(R.id.rvContacts) as RecyclerView
        // Initialize contacts
        contacts = Contact.createContactsList(20)
        // Create adapter passing in the sample user data
        val adapter = ContactsAdapter(contacts)
        // Attach the adapter to the recyclerview to populate items
        rvContacts.adapter = adapter
        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(this)
        // That's all!
    }

    /** Check if user is logged into the app */
    private fun verifyUserIsLoggedIn(): Boolean {
        val uid = FirebaseAuth.getInstance().uid
        Log.e("UID", uid.toString())

        return if (uid == null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            false
        } else {
            true
        }
    }

    /** Load menu onto action bar */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_feed, menu)
        return true
    }

    /** Used to handle user menu clicks on action bar */
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