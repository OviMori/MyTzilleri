package com.example.mytzilleri


data class Utente(
        var nome: String = "",
        var cognome: String = "",
        var email: String = "",
        var password : String = "",
        var bio : String = ""
) : HashSet<String>() {

    fun  creaNuovoUtenteDaStringa(str : String){
        var strArrUtente = str.split("*")
        this.nome = strArrUtente[0]
        this.cognome = strArrUtente[1]
        this.email = strArrUtente[2]
        this.password = strArrUtente[3]
        this.bio = strArrUtente[4]
    }

    override fun toString(): String {
        return ""+this.nome+"*"+this.cognome+"*"+this.email+"*"+this.password+"*"+this.bio
    }
}