package com.example.thesocialapp

import java.util.*

class Contact (val name: String, val isOnline: Boolean){
    companion object {
        /** Hard coded contacts/events for HowdyHack demo
         * Normally, we would load from event info from database */
        private var lastContactId = 9
        fun createContactsList(numContacts: Int): ArrayList<Contact> {
            val contacts = ArrayList<Contact>()
            contacts.add(Contact("Career Fair at Kyle Field", false))
            contacts.add(Contact("HowdyHack at MSC", true))
            contacts.add(Contact("Group Study at Evans", true))
            contacts.add(Contact("Northgate Grill Out", true))
            contacts.add(Contact("Basketball at the Rec", true))
            contacts.add(Contact("Sbisa Food Festival", false))
            contacts.add(Contact("MSC Aggie Movie Night", true))
            contacts.add(Contact("Rec-A-Palooza", true))
            contacts.add(Contact("Class Pics at Kyle Field", false))

            // placeholder contacts
            for (i in 9..numContacts) {
                contacts.add(Contact("Event " + (++lastContactId), i <= numContacts / 2))
            }
            return contacts
        }
    }
}