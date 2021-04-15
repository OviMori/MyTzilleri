package com.example.mytzilleri;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HolderProduct extends RecyclerView.ViewHolder {

    TextView nomeProdotto, quantita;


    public HolderProduct(View view){
        super(view);
        //qua si dovrebbero definire i click_listener per la view di ViewHolder
        nomeProdotto = view.findViewById(R.id.list_prodotti_nome_prodotto);
        quantita = view.findViewById(R.id.list_prodotti_quantita);


    }

    public void init(String prodName, String qt){
        nomeProdotto.setText(prodName);
        if(qt != null){
            quantita.setText(qt);
        }
    }

}