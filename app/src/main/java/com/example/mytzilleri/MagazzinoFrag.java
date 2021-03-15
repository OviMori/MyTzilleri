package com.example.mytzilleri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.controls.actions.FloatAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MagazzinoFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MagazzinoFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //------------------------------------------------------
    //Mie variabili
    Button bottone;

    private RecyclerView recyclerView;
    FloatingActionButton aggiungiButton;
    //------------------------------------------------------

    public MagazzinoFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MagazzinoFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static MagazzinoFrag newInstance(String param1, String param2) {
        MagazzinoFrag fragment = new MagazzinoFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_magazzino, container, false);

        bottone = v.findViewById(R.id.bottone);
        recyclerView = v.findViewById(R.id.recycler_view_prodotti);
        aggiungiButton = v.findViewById(R.id.aggiungi_prodotto_button);

        //Elementi della recyclerview
        CustomAdapter adapter = new CustomAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        //-----------------------------------------------------------------------

        //questo va eseguito quando premo il pulsante salva
        adapter.notifyDataSetChanged();

        initRecyclerView(adapter, layoutManager);

        aggiungiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addElement(adapter, layoutManager);
                adapter.notifyDataSetChanged();
            }
        });

        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getContext(),  PaginaProdotto.class);
                startActivity(inte);

            }
        });

        return v;
    }

    /**
     * Questa funzione va inserita nella classe che gestisce il layout con la recycler view
     *
     */
    private void initRecyclerView(CustomAdapter adapter, RecyclerView.LayoutManager layoutManager){

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void addElement(CustomAdapter adapter, RecyclerView.LayoutManager layoutManager){

        Prodotto infoProdotto = new Prodotto();
        //Gson gson = new Gson()

        Intent newProdotto = new Intent(getContext(), PaginaProdotto.class);
        newProdotto.putExtra("infoProdotto", infoProdotto);    //passo il riferimento all oggetto di tipo prodotto
        startActivity(newProdotto);


        //questo va eseguito quando premo il pulsante salva
        adapter.reloadList(infoProdotto);   //qua devo passare un oggetto di tipo prodotto
        adapter.notifyDataSetChanged();
    }

}

















