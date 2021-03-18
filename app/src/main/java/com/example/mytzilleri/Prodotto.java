package com.example.mytzilleri;

import java.io.Serializable;

public class Prodotto  implements Serializable {
    private String nomeProdotto;
    private int quantita;
    private int notificaEsaurimentoScorte;
    private String categoria;


    private String nomeFornitore, emailFornitore;
    private String telFornitore;


    private int idKey;
    private static int count;


    public Prodotto(){

        this.nomeProdotto = " ";
        this.nomeFornitore = " ";  //valore di default
        this.emailFornitore = " ";  //valore di default
        this.telFornitore = " ";  //valore di default
        this.setIdKey(count);
        count++;

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

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getNotificaEsaurimentoScorte() {
        return notificaEsaurimentoScorte;
    }

    public void setNotificaEsaurimentoScorte(int notificaEsaurimentoScorte) {
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
