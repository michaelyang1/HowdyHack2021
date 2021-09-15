package com.example.thesocialapp

import java.util.*

class Contact (val name: String, val isOnline: Boolean){
    companion object {
        private var lastContactId = 4
        fun createContactsList(numContacts: Int): ArrayList<Contact> {
            val contacts = ArrayList<Contact>()
            contacts.add(Contact("Career fair at Kyle Field", false))
            contacts.add(Contact("HowdyHack at MSC", true))
            contacts.add(Contact("Group study at Evans", true))
            contacts.add(Contact("Northgate grill out", true))

            for (i in 5..numContacts) {
                contacts.add(Contact("Event " + (++lastContactId), i <= numContacts / 2))
            }
            return contacts
        }
    }
}