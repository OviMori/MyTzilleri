package com.example.mytzilleri;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ForegroundLinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //------------------------------------------------------
    //Mie variabili
    FloatingActionButton fab;
    ImageView showSearchLayoutButton;
    View messageLayoutSearch;
    RecyclerView recyclerView;
    ChatAdapter adapter;
    //------------------------------------------------------

    public FragmentChat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentChat newInstance(String param1, String param2) {
        FragmentChat fragment = new FragmentChat();
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
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        showSearchLayoutButton = v.findViewById(R.id.message_search_top_icon);
        messageLayoutSearch = v.findViewById(R.id.message_layout_search);
        fab = v.findViewById(R.id.fab);
        recyclerView = v.findViewById(R.id.messaggi_recycler_view);

        initRecyclerView();


        /**
         * Consente di far apparire/scomparire la barra di ricerca dei contatti
         */
        showSearchLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageLayoutSearch.getVisibility() == View.GONE){
                    messageLayoutSearch.setVisibility(View.VISIBLE);
                }else{
                    messageLayoutSearch.setVisibility(View.GONE);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewChat();
            }
        });

        return v;
    }

    private void createNewChat(){
        //lanciare qualcosa per creare la nuova chat, idea: un CustomPicker
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ChatAdapter(DataRepositoryChat.INSTANCE.getNameChatsList());
        recyclerView.setAdapter(adapter);
    }
}