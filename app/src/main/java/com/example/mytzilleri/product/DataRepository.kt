package com.example.mytzilleri.product

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.mytzilleri.login.User

//TODO aggiungere la classe per passare il contesto a DataRepository

object DataRepository {

    private const val MY_SHARED_PREF_PRODOTTI = "LISTA_PRODOTTI"
    private const val MY_SHARED_PREF_UTENTI = "INFO_UTENTI"
    private const val MY_SHARED_PREF_UTENTE_CORRENTE = "UTENTE_CORRENTE"
    private const val UTENTE_CORRENTE_KEY = "key_current_user"


    private lateinit var sharPrefUsers : SharedPreferences
    private lateinit var sharPrefMyUser : SharedPreferences
    private lateinit var sharPrefMyProducts : SharedPreferences

    fun init(context : Context){
        sharPrefUsers = context.getSharedPreferences(MY_SHARED_PREF_UTENTI, Context.MODE_PRIVATE)
        sharPrefMyUser = context.getSharedPreferences(MY_SHARED_PREF_UTENTE_CORRENTE, Context.MODE_PRIVATE)
        sharPrefMyProducts = context.getSharedPreferences(MY_SHARED_PREF_PRODOTTI, Context.MODE_PRIVATE)
    }

    fun dropAllProd(){
        sharPrefMyProducts.edit().clear().apply()
    }

    //Products managment
    fun saveProduct(prod: Product){
        sharPrefMyProducts.edit().putString(prod.nomeProdotto, prod.toString()).apply()
    }

    fun getProdList(): List<Product>{
        var prodList: ArrayList<Product> = ArrayList()
        val userListFromPreferences: Map<String, *> = sharPrefMyProducts.all

        for(user in userListFromPreferences){
            val tempProd = Product()
            tempProd.fromString(user.value.toString())   //create new user
            prodList.add(tempProd)
        }
        return prodList
    }


    fun getProduct(nameProd: String ): Product?{
        val prod = Product()
        var strTemp: String

        if(containsProduct(nameProd)){
            strTemp = sharPrefMyProducts.getString(nameProd, "").toString()

            if(!strTemp.equals("")){
                prod.fromString(strTemp)
                return prod;
            }
        }
        return null
    }

    fun containsProduct(nameProd: String): Boolean{
        return sharPrefMyProducts.contains(nameProd)
    }


    //Users managment
    fun getUsersList() : List<User>{
        var userList: ArrayList<User> = ArrayList()
        val userListFromPreferences: Map<String, *> = sharPrefUsers.all


        for(user in userListFromPreferences){
            val tempUser = User()
            tempUser.creaNuovoUtenteDaStringa(user.value.toString())   //create new user
            userList.add(tempUser)
        }

        for(tempUser in userList){
            Log.i("For test after drop", tempUser.toString())
        }

        return userList
    }

    fun removeAllAccounts(){
        sharPrefUsers.edit().clear().apply()
    }


    fun dropUser(email : String) : Boolean{
        if(sharPrefUsers.contains(email)){
            sharPrefUsers.edit().remove(email).apply()
            return true
        }
        return false
    }



    fun getSharedPref() : SharedPreferences{
        return sharPrefUsers
    }

    fun salvaCambioPassword(newPassword : String){
        val userWithNewPassword = getCurrentUser()
        userWithNewPassword.password = newPassword

        //change either current user credential and credential in the list of all users
        salvaUtenteCorrente(userWithNewPassword)
        salvaUtente(userWithNewPassword)
    }

    fun userExist(email : String) : Boolean{
        var tempAdmin = sharPrefUsers.getString(email, "")
        if(tempAdmin.equals("")){   //if admin does not exist
            return false
        }
        return true
    }

    fun salvaUtenteCorrente(currentUser : User){
        Log.i("salvataggio Utente  corrente ", currentUser.toString())
        sharPrefMyUser.edit().putString(UTENTE_CORRENTE_KEY, currentUser.toString()).apply()
    }

    fun salvaUtente(newUser : User){
        salvaCredenziali(newUser)
    }

    fun getCurrentUser() : User {
        val strCurrentUser = sharPrefMyUser.getString(UTENTE_CORRENTE_KEY, "") as String
        val currentUser = User()
        Log.i("recupero Utente  corrente ", ""+strCurrentUser)

        if(!strCurrentUser.equals("")){
            currentUser.creaNuovoUtenteDaStringa(strCurrentUser)
        }
        return currentUser
    }

    /**
     * Return empty user data if not exit account with @param emil associated
     */
    fun getUser(email : String) : User {
        val userGeneratedFromString = User()
        val utenteInString : String = sharPrefUsers.getString(email, "") as String

        Log.i("Username passed ", email)
        Log.i("Utente generato ", utenteInString)

        if(!utenteInString.equals("")){ //if user exist
            userGeneratedFromString.creaNuovoUtenteDaStringa(utenteInString)
        }
        return userGeneratedFromString
    }

    fun createAdminAccount(){
        var utenteAdmin = User("admin", "admin", "admin@", "", "")
        salvaCredenziali(utenteAdmin)
    }

    fun adminExist() : Boolean{
        var tempAdmin = sharPrefUsers.getString("admin", "")
        if(tempAdmin.equals("")){   //if admin does not exist
            return false
        }
        return true
    }

    private fun salvaCredenziali(newUser : User){
        Log.i("InfoUtenteRegistrato", newUser.toString())
        sharPrefUsers.edit().putString(newUser.email, newUser.toString()).apply()
    }


}