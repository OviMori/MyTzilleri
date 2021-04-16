package com.example.mytzilleri.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytzilleri.R;
import com.example.mytzilleri.product.DataRepository;
import com.example.mytzilleri.product.PaginaProdotto;
import com.example.mytzilleri.product.ProductAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentStock#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStock extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //------------------------------------------------------
    public final int REQUEST_CODE = 0;

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    FloatingActionButton aggiungiButton;
    //------------------------------------------------------

    public FragmentStock() {
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
    public static FragmentStock newInstance(String param1, String param2) {
        FragmentStock fragment = new FragmentStock();
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


        recyclerView = v.findViewById(R.id.recycler_view_prodotti);
        aggiungiButton = v.findViewById(R.id.aggiungi_prodotto_button);

        initRecyclerView();

        aggiungiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addElement();
            }
        });

        return v;
    }

    /**
     * Questa funzione va inserita nella classe che gestisce il layout con la recycler view
     *
     */
    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ProductAdapter(DataRepository.INSTANCE.getProdList());
        recyclerView.setAdapter(adapter);
    }

    private void addElement(){
        Intent newProdotto = new Intent(getContext(), PaginaProdotto.class);
        startActivityForResult(newProdotto, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //viene restituito il prodotto appena creato
        //in realta basta anche solo aggiornare la recycler view senza passare il prodotto,
        //il fatto di aver aggiunto un elemento nelle sharedPreferences basta a far capire alla recycler view qual e' l elemento da aggiungere (credo)
        Log.i("onActivityResult", "Back from activity");
        adapter.updateList(DataRepository.INSTANCE.getProdList());
        adapter.notifyDataSetChanged();
    }
}

















