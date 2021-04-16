package com.example.mytzilleri.chat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytzilleri.R;


public class HolderChat extends RecyclerView.ViewHolder {

    TextView userName, lastText ;


    public HolderChat(View view){
        super(view);
        //qua si dovrebbero definire i click_listener per la view di ViewHolder
        userName = view.findViewById(R.id.list_prodotti_nome_prodotto);
        lastText = view.findViewById(R.id.list_prodotti_quantita);


    }

    public void init(String userNamePar, String lastTextPar){
        userName.setText(userNamePar);
        lastText.setText(lastTextPar);
    }

}
 