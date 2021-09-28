package com.example.thesocialapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter (private val mContacts : List <Contact>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById<TextView>(R.id.contact_name)
        val messageButton: Button = itemView.findViewById<Button>(R.id.message_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_contact, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    /** Bind information to recyclerview adapter */
    override fun onBindViewHolder(viewHolder: ContactsAdapter.ViewHolder, position: Int) {
        val contact: Contact = mContacts[position]
        val textView = viewHolder.nameTextView;
        val button = viewHolder.messageButton;

        textView.setText(contact.name)
        // change contact status depending on if online
        if (contact.isOnline) {
            button.text = "RSVP"
            button.setBackgroundColor(Color.parseColor("green"))
        } else {
            button.text = "Closed"
            button.setBackgroundColor(Color.parseColor("red"))
        }

    }

    override fun getItemCount(): Int {
        return mContacts.size
    }


}