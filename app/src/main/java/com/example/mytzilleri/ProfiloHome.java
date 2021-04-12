package com.example.mytzilleri;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mytzilleri.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfiloHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfiloHome extends Fragment {

    private FragmentHomeBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //------------------------------------------------------
    //Mie variabili
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    FragmentAListener listener; //questo listener aspetta che l activity utilizzi il nostro fragment
    FrameLayout framePromemoriaUtente, frameInfoUtente;
    Button salvaDatiUtente;
    LinearLayout linearLayoutInfoUtente, linearLayoutInEsaurimento, linearLayoutNuoviMessaggi;
    ImageButton inEsaurimentoArrowHome, nuoviMessaggiArrowHome;
    RecyclerView inEsaurimentoRecycler, nuoviMessaggiRecycler;

    EditText edit_nome_utente, edit_cognome_utente, edit_indirizzo, edit_cellulare, edit_bio;
    TextView text_top_nome, text_top_cognome, text_top_indirizzo, text_top_cellulare, text_top_bio;

    //------------------------------------------------------

    public ProfiloHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfiloFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfiloHome newInstance(String param1, String param2) {
        ProfiloHome fragment = new ProfiloHome();
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


    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false); //MODIFICA: Salvato il valore di ritorno in "View v"

        viewPager = binding.viewPager;
        tabLayout = binding.tabController;
        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new TabViewMessaggiFrag(), "Tab Messaggi");
        adapter.addFragment(new TabViewPersonaleFrag(), "Tab Personale");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //framePromemoriaUtente = binding.findViewById(R.id.frame_promemoria_utente);
        //frameInfoUtente = binding.findViewById(R.id.frame_info_utente);
        //linearLayoutInfoUtente = binding.findViewById(R.id.linear_layout_info_utente);

        //inEsaurimentoArrowHome = binding.findViewById(R.id.in_esaurimento_arrow);
        //nuoviMessaggiArrowHome = binding.findViewById(R.id.nuovi_messaggi_arrow);

        //inEsaurimentoRecycler = binding.findViewById(R.id.recycler_view_in_esaurimento_home);
        //nuoviMessaggiRecycler = binding.findViewById(R.id.recycler_view_nuovi_messaggi_home);

        //linearLayoutInEsaurimento = binding.findViewById(R.id.linear_layout_in_esaurimento);
        //linearLayoutNuoviMessaggi = binding.findViewById(R.id.linear_layout_nuovi_messaggi);


        //text_top_nome = binding.findViewById(R.id.top_nome_utente);
        //text_top_cognome = binding.findViewById(R.id.top_cognome_utente);
        //text_top_cellulare = binding.findViewById(R.id.top_cellulare);
        //text_top_indirizzo = binding.findViewById(R.id.top_indirizzo);
        //text_top_bio = binding.findViewById(R.id.top_bio);

        //salvaDatiUtente = binding.findViewById(R.id.aggiorna_dati_utente);




        return binding.getRoot();
    }


    private void setCampiConDatiUtente() {
        SharedPreferences pref = this.getActivity().getSharedPreferences(getString(R.string.preference_file_key), 0);

        String savedNome = pref.getString(getString(R.string.saved_nome_utente), "");
        String savedCognome = pref.getString(getString(R.string.saved_cognome_utente), "");
        String savedCellulare = pref.getString(getString(R.string.saved_cellulare_utente), "");
        String savedIndirizzo = pref.getString(getString(R.string.saved_indirizzo_utente), "");
        String savedBio = pref.getString(getString(R.string.saved_bio_utente), "");
        String savedEmail = pref.getString(getString(R.string.saved_email_login), "");
        String savedPassword = pref.getString(getString(R.string.saved_password_login), "");

        text_top_nome.setText(savedNome);
        edit_nome_utente.setText(savedNome);

        text_top_cognome.setText(savedCognome);
        edit_cognome_utente.setText(savedCognome);

        text_top_cellulare.setText(savedCellulare);
        edit_cellulare.setText(savedCellulare);

        text_top_indirizzo.setText(savedIndirizzo);
        edit_indirizzo.setText(savedIndirizzo);

        text_top_bio.setText(savedBio);
        edit_bio.setText(savedBio);
    }





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}