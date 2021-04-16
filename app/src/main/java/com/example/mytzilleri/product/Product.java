package com.example.mytzilleri.product;

import java.io.Serializable;

public class Product implements Serializable {
    private String nomeProdotto;
    private String quantita;
    private String notificaEsaurimentoScorte;
    private String categoria;


    private String nomeFornitore, emailFornitore;
    private String telFornitore;


    private int idKey;
    private static int count;


    public Product(){

        this.nomeProdotto = " ";
        this.categoria = " ";
        this.nomeProdotto = " ";
        this.nomeFornitore = " ";  //valore di default
        this.emailFornitore = " ";  //valore di default
        this.setIdKey(count);
        count++;
    }

    public Product fromString(String prodInString){
        String[] infoProdArray = prodInString.split("#");
        if(infoProdArray.length == 7){
            this.nomeProdotto = infoProdArray[0];
            this.quantita = infoProdArray[1];
            this.notificaEsaurimentoScorte = infoProdArray[2];
            this.categoria = infoProdArray[3];
            this.nomeFornitore = infoProdArray[4];
            this.emailFornitore = infoProdArray[5];
            this.telFornitore = infoProdArray[6];
        }


        return this;
    }

    @Override
    public String toString(){
        return ""+this.nomeProdotto+"#"+this.quantita+"#"+this.notificaEsaurimentoScorte+"#"+this.categoria+"#"+this.nomeFornitore+"#"+this.emailFornitore+"#"+this.telFornitore;
    }

    public int getIdKey() {
        return idKey;
    }

    public void setIdKey(int idKey) {
        this.idKey = idKey;
    }


    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public String getNotificaEsaurimentoScorte() {
        return notificaEsaurimentoScorte;
    }

    public void setNotificaEsaurimentoScorte(String notificaEsaurimentoScorte) {
        this.notificaEsaurimentoScorte = notificaEsaurimentoScorte;
    }

    public String getNomeFornitore() {
        return nomeFornitore;
    }

    public void setNomeFornitore(String nomeFornitore) {
        this.nomeFornitore = nomeFornitore;
    }

    public String getEmailFornitore() {
        return emailFornitore;
    }

    public void setEmailFornitore(String emailFornitore) {
        this.emailFornitore = emailFornitore;
    }

    public String getTelFornitore() {
        return telFornitore;
    }

    public void setTelFornitore(String telFornitore) {
        this.telFornitore = telFornitore;
    }
}
