package com.example.mytzilleri;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<HolderProduct> {

    private List<Product> listaProdotti;

    public ProductAdapter(List<Product> list){
        this.listaProdotti = list;
    }

    //Creazione di nuove views (invocate dal Layout Manager)
    @Override
    @NonNull
    public HolderProduct onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prodotto_row_item, viewGroup, false);
        return new HolderProduct(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HolderProduct viewHolder, int position){

        viewHolder.init(listaProdotti.get(position).getNomeProdotto(), listaProdotti.get(position).getQuantita());
    }

    //Restituisce la dimensione del dataset (invocato dal Layout Manager)
    @Override
    public int getItemCount(){
        return listaProdotti.size();
    }

    public void updateList(List<Product> newLIst){
        this.listaProdotti = newLIst;
    }

}