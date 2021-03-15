package com.example.mytzilleri;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private static List<Prodotto> localDataSet = new ArrayList<Prodotto>();

    /**
     * Creazione della referenza al tipo di view che si vuole utilizzare
     * (ViewHolder personalizzato)
     */

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public ViewHolder(View view){
            super(view);

            //qua si dovrebbero definire i click_listener per la view di ViewHolder

            textView = (TextView) view.findViewById(R.id.recycler_view_prodotti);
        }

        public TextView getTextView(){
            return textView;
        }
    }

    /**
     * Inizializzazione del dataset utilizzato dall adapter
     */
    public CustomAdapter(){
    }

    //Creazione di nuove views (invocate dal Layout Manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        //Creazione di una nuova view che definisce il layout della lista di item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prodotto_row_item, viewGroup, false);

        //qua posso definire come gli elementi della mia recyclerView devono essere mostrati,
        //La variaibile view ha il riferimento al layout del singolo elemento

        return new ViewHolder(view);
    }

    //Rimpiazza il contenuto  della view (invocato dal Layout Manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        //2 passaggi:
        //1) si recupera l elemento nella posizione indicata da position
        //2) si rimpiazza quell elemento con il nuovo elemento

        TextView nomeProdottoElenco, quantitaElenco;

        nomeProdottoElenco = viewHolder.itemView.findViewById(R.id.list_prodotti_nome_prodotto);
        quantitaElenco = viewHolder.itemView.findViewById(R.id.list_prodotti_quantita);


        nomeProdottoElenco.setText(localDataSet.get(position).getNomeProdotto());
        quantitaElenco.setText(Integer.toString(localDataSet.get(position).getQuantita()));

    }

    //Restituisce la dimensione del dataset (invocato dal Layout Manager)
    @Override
    public int getItemCount(){
        return localDataSet.size();
    }

    public void reloadList(Prodotto mpList) {
        //creazione di un nuovo elemento della lista
        localDataSet.add(mpList);

        Log.i("telefono-----------------------------------------------------", mpList.getTelFornitore());

    }
}