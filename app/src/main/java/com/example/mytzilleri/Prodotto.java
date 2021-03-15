package com.example.mytzilleri;

import java.io.Serializable;

public class Prodotto  implements Serializable {
    private String nomeProdotto, categoria;
    private int quantita, notificaEsaurimentoScorte;

    private String nomeFornitore, emailFornitore;
    private String telFornitore;


    public Prodotto(){
        this.nomeProdotto = " ";
        this.categoria = " ";
        this.quantita = 0;
        this.notificaEsaurimentoScorte = 0;  //valore di default
        this.nomeFornitore = " ";  //valore di default
        this.emailFornitore = " ";  //valore di default
        this.telFornitore = " ";  //valore di default
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
