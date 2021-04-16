package com.example.mytzilleri.chat

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object DataRepositoryContact {

    private const val MY_SHARED_PREF_CONTACT = "CONTACT_LIST"
    private lateinit var sharPrefContact: SharedPreferences

    fun init(context: Context){
        sharPrefContact = context.getSharedPreferences(MY_SHARED_PREF_CONTACT, Context.MODE_PRIVATE)
    }

    //Contact managment
    fun getContactList() : List<Contact>{
        var contactList: ArrayList<Contact> = ArrayList()
        val ContactListFromPreferences: Map<String, *> = sharPrefContact.all

        for(contact in ContactListFromPreferences){
            val tempContact = Contact()
            tempContact.createNewUserFromString(contact.value.toString())   //create new Contact
            contactList.add(tempContact)
        }

        for(tempContact in contactList){
            Log.i("For test after drop", tempContact.toString())
        }

        return contactList
    }

    fun removeAllAccounts(){
        sharPrefContact.edit().clear().apply()
    }

    fun dropContact(email : String) : Boolean{
        if(sharPrefContact.contains(email)){
            sharPrefContact.edit().remove(email).apply()
            return true
        }
        return false
    }

    fun contactExist(email : String) : Boolean{
        var tempAdmin = sharPrefContact.getString(email, "")
        if(tempAdmin.equals("")){   //if admin does not exist
            return false
        }
        return true
    }


    fun saveContact(newContact : Contact){
        saveCredentials(newContact)
    }

    /**
     * Return empty Contact data if not exit account with @param emil associated
     */
    fun getContact(email : String) : Contact {
        val ContactGeneratedFromString = Contact()
        val utenteInString : String = sharPrefContact.getString(email, "") as String

        if(!utenteInString.equals("")){ //if Contact exist
            ContactGeneratedFromString.createNewUserFromString(utenteInString)
        }
        return ContactGeneratedFromString
    }

    private fun saveCredentials(newContact : Contact){
        Log.i("InfoUtenteRegistrato", newContact.toString())
        sharPrefContact.edit().putString(newContact.email, newContact.toString()).apply()
    }

}