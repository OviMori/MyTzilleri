package com.example.mytzilleri;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Prodotto> listaProdotti;
    private OnNoteListener mOnNoteListener;

    public CustomAdapter(List<Prodotto> list, OnNoteListener onNoteListener){
        this.listaProdotti = list;
        this.mOnNoteListener = onNoteListener;
    }

    //Creazione di nuove views (invocate dal Layout Manager)
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        //Creazione di una nuova view che definisce il layout della lista di item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prodotto_row_item, viewGroup, false);

        //qua posso definire come gli elementi della mia recyclerView devono essere mostrati,
        //La variaibile view ha il riferimento al layout del singolo elemento

        return new ViewHolder(view, mOnNoteListener);
    }

    /**
     * Creazione della referenza al tipo di view che si vuole utilizzare
     * (ViewHolder personalizzato)
     */

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nomeProdotto, quantita;
        LinearLayout linerItem;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener){
            super(view);
            //qua si dovrebbero definire i click_listener per la view di ViewHolder
            nomeProdotto = view.findViewById(R.id.list_prodotti_nome_prodotto);
            quantita = view.findViewById(R.id.list_prodotti_quantita);
            linerItem = view.findViewById(R.id.clickable_item_magazzino);

            this.onNoteListener = onNoteListener;

            linerItem.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            onNoteListener.onNoteClick(this.getAdapterPosition());
        }

    }

    public Prodotto get(int position){
        return listaProdotti.get(position);
    }

    /**
     * Inizializzazione del dataset utilizzato dall adapter
     */

    public List<Prodotto> getList(){
        return listaProdotti;
    }

    public void swap(List<Prodotto> datas)
    {
        listaProdotti.clear();
        listaProdotti.addAll(datas);
        notifyDataSetChanged();
    }



    //Rimpiazza il contenuto  della view (invocato dal Layout Manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        //2 passaggi:
        //1) si recupera l elemento nella posizione indicata da position
        //2) si rimpiazza quell elemento con il nuovo elemento

        viewHolder.nomeProdotto.setText(listaProdotti.get(position).getNomeProdotto());
        viewHolder.quantita.setText(Integer.toString(listaProdotti.get(position).getQuantita()));
    }

    //Restituisce la dimensione del dataset (invocato dal Layout Manager)
    @Override
    public int getItemCount(){
        return listaProdotti.size();
    }


    /*public void reloadList(Prodotto item) {
        //creazione di un nuovo elemento della lista

            listaProdotti.add(item);

    }*/


    public void reloadList(Prodotto item) {
        //creazione di un nuovo elemento della lista
        int idKeyProduct = item.getIdKey();

        if(listaProdotti.size() == 0){
            listaProdotti.add(item);
        }else{
            if(listaProdotti.size() <= idKeyProduct){
                listaProdotti.add(item);
            }else{
                listaProdotti.set(idKeyProduct, item);
            }
        }


    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}