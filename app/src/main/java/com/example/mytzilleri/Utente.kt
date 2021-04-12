package com.example.mytzilleri


data class Utente(
        var nome: String = "",
        var cognome: String = "",
        var email: String = "",
        var password : String = "",
        var eliminare : Boolean = false,
        var admin : Int = 0
) : HashSet<String>() {

    fun  creaNuovoUtenteDaStringa(str : String){
        var strArrUtente = str.split("*")
        this.nome = strArrUtente[0]
        this.cognome = strArrUtente[1]
        this.email = strArrUtente[2]
        this.password = strArrUtente[3]

        if(strArrUtente[4] == "true"){
            this.eliminare = true
        }

        if(strArrUtente[5] == "1"){
            this.admin = 1
        }else{
            this.admin = 0
        }
    }

    override fun toString(): String {
        return ""+this.nome+"*"+this.cognome+"*"+this.email+"*"+this.password+"*"+this.eliminare+"*"+this.admin
    }
}