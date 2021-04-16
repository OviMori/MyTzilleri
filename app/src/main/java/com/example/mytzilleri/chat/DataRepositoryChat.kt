package com.example.mytzilleri.chat

import android.content.Context
import android.content.SharedPreferences

object DataRepositoryChat {

    private const val MY_SHARED_PREF_CHAT = "LISTA_CHAT"
    private lateinit var sharPrefChat: SharedPreferences

    fun init(context: Context){
        sharPrefChat = context.getSharedPreferences(MY_SHARED_PREF_CHAT, Context.MODE_PRIVATE)
    }

    /**
     * Return empty user data if not exit account with @param emil associated
     */
    fun getChat(email : String) : Chat?{
        var newChat = Chat(" ", " ")
        val chatInString : String = sharPrefChat.getString(email, "") as String

        if(newChat.toObject(chatInString))
            return newChat
        else
            return null
    }

    fun getNameChatsList(): List<String>{
        var chatListSharedPref: Map<String, *> = sharPrefChat.all
        var emailChatList: List<String> = ArrayList()

        for(str in chatListSharedPref.keys){
            emailChatList.toMutableList().add(str)
        }
        return emailChatList
    }

    private fun saveChat(chat : Chat){
        sharPrefChat.edit().putString(chat.email, chat.toString()).apply()
    }


}