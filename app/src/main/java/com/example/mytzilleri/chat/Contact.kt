package com.example.mytzilleri.chat

data class Contact(
        var nome: String = "",
        var cognome: String = "",
        var email: String = "",
        var cell : String = "",
) : HashSet<String>() {

    fun  createNewUserFromString(str : String){
        var strArrUtente = str.split("*")
        this.nome = strArrUtente[0]
        this.cognome = strArrUtente[1]
        this.email = strArrUtente[2]
        this.cell = strArrUtente[3]

    }

    override fun toString(): String {
        return ""+this.nome+"*"+this.cognome+"*"+this.email+"*"+this.cell
    }

    fun setTempAdmin(){
        this.nome = "admin"
        this.cognome = "admin"
        this.email = "admin@"
        this.cell = "123456"
    }
}