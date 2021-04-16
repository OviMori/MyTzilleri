package com.example.mytzilleri.chat;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chat {

    private String email;
    private String nomeUtente;
    private ArrayList<String> messagesList;

    public Chat(String email, String nome){
        this.email = email;
        this.nomeUtente = nome;
        this.messagesList = new ArrayList<>();
    }

    public boolean toObject(String dataChat){
        if(dataChat == null)
            return false;

        if(dataChat.trim().equals(""))
            return false;

        String[] strFirtsSplit = dataChat.split("##");
        if(strFirtsSplit.length < 2)
            return false;

        this.nomeUtente = strFirtsSplit[0];

        String[] strSecSplit = strFirtsSplit[1].split("&&");
        if(strSecSplit.length < 2)
            return false;

        this.email = strSecSplit[0];

        String[] messages = strSecSplit[1].split("%%%");
        if(messages.length > 0){
            this.messagesList.addAll(Arrays.asList(messages));
            return true;
        }
        return false;
    }

    @NotNull
    @Override
    public String toString(){
        String tempStrChat = "";
        tempStrChat += nomeUtente+"##"; //add name
        tempStrChat += email+"&&"; //add email

        for(String str : messagesList){
            tempStrChat += str+"%%%";   //add all messages
        }
        return tempStrChat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
}
