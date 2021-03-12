package com.example.mytzilleri;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfiloFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfiloFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //------------------------------------------------------
    //Mie variabili
    FragmentAListener listener; //questo listener aspetta che l activity utilizzi il nostro fragment
    FrameLayout framePromemoriaUtente, frameInfoUtente;
    Button salvaDatiUtente;
    LinearLayout linearLayoutInfoUtente, linearLayoutInEsaurimento, linearLayoutNuoviMessaggi;
    ImageButton inEsaurimentoArrowHome, nuoviMessaggiArrowHome;
    RecyclerView inEsaurimentoRecycler, nuoviMessaggiRecycler;

    EditText edit_nome_utente, edit_cognome_utente, edit_indirizzo, edit_cellulare, edit_bio;
    TextView text_top_nome, text_top_cognome, text_top_indirizzo, text_top_cellulare, text_top_bio;

    //------------------------------------------------------

    public ProfiloFrag() {
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
    public static ProfiloFrag newInstance(String param1, String param2) {
        ProfiloFrag fragment = new ProfiloFrag();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profilo, container, false); //MODIFICA: Salvato il valore di ritorno in "View v"

        framePromemoriaUtente = v.findViewById(R.id.frame_promemoria_utente);
        frameInfoUtente = v.findViewById(R.id.frame_info_utente);
        linearLayoutInfoUtente = v.findViewById(R.id.linear_layout_info_utente);

        inEsaurimentoArrowHome = v.findViewById(R.id.in_esaurimento_arrow);
        nuoviMessaggiArrowHome = v.findViewById(R.id.nuovi_messaggi_arrow);

        inEsaurimentoRecycler = v.findViewById(R.id.recycler_view_in_esaurimento_home);
        nuoviMessaggiRecycler = v.findViewById(R.id.recycler_view_nuovi_messaggi_home);

        linearLayoutInEsaurimento = v.findViewById(R.id.linear_layout_in_esaurimento);
        linearLayoutNuoviMessaggi = v.findViewById(R.id.linear_layout_nuovi_messaggi);

        edit_nome_utente = v.findViewById(R.id.info_utente_nome);
        edit_cognome_utente = v.findViewById(R.id.info_utente_cognome);
        edit_indirizzo = v.findViewById(R.id.info_utente_indirizzo);
        edit_cellulare = v.findViewById(R.id.info_utente_cellulare);
        edit_bio = v.findViewById(R.id.info_utente_bio);

        text_top_nome = v.findViewById(R.id.top_nome_utente);
        text_top_cognome = v.findViewById(R.id.top_cognome_utente);
        text_top_cellulare = v.findViewById(R.id.top_cellulare);
        text_top_indirizzo = v.findViewById(R.id.top_indirizzo);
        text_top_bio = v.findViewById(R.id.top_bio);

        salvaDatiUtente = v.findViewById(R.id.aggiorna_dati_utente);

        setCampiConDatiUtente();

        salvaDatiUtente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggiornaDatiUtente();
            }
        });


        /**
         * Gestisce il click che fa apparire/scomparire la RecyclerView dei prodotti in esaurimento
         * Viene gestita anche la modifica del verso della freccia
         */
        linearLayoutInEsaurimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inEsaurimentoRecycler.getVisibility() == View.GONE){
                    inEsaurimentoRecycler.setVisibility(View.VISIBLE);
                    inEsaurimentoArrowHome.setBackgroundResource(R.drawable.drop_menu_arrow_down);
                }else{
                    inEsaurimentoRecycler.setVisibility(View.GONE);
                    inEsaurimentoArrowHome.setBackgroundResource(R.drawable.drop_menu_arrow_right);
                }
            }
        });

        /**
         * Gestisce il click che fa apparire/scomparire la RecyclerView dei messaggi dell utente
         * Viene gestita anche la modifica del verso della freccia
         */
        linearLayoutNuoviMessaggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nuoviMessaggiRecycler.getVisibility() == View.GONE){
                    nuoviMessaggiRecycler.setVisibility(View.VISIBLE);
                    nuoviMessaggiArrowHome.setBackgroundResource(R.drawable.drop_menu_arrow_down);
                }else{
                    nuoviMessaggiRecycler.setVisibility(View.GONE);
                    nuoviMessaggiArrowHome.setBackgroundResource(R.drawable.drop_menu_arrow_right);
                }
            }
        });

        /**
         * Gestisce il click che fa apparire/nascondere la sezione con le info
         * personali dell utente
         */
        linearLayoutInfoUtente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(framePromemoriaUtente.getVisibility() == View.GONE){
                    frameInfoUtente.setVisibility(View.GONE);
                    framePromemoriaUtente.setVisibility(View.VISIBLE);

                }else{
                    framePromemoriaUtente.setVisibility(View.GONE);
                    frameInfoUtente.setVisibility(View.VISIBLE);
                }
            }
        });


        return v;
    }

    private void aggiornaDatiUtente(){
        String newNome = edit_nome_utente.getText().toString();
        String newCognome = edit_cognome_utente.getText().toString();
        String newCellulare = edit_cellulare.getText().toString();
        String newIndirizzo = edit_indirizzo.getText().toString();
        String newBio = edit_bio.getText().toString();

        SharedPreferences.Editor edit = this.getActivity().getSharedPreferences(getString(R.string.preference_file_key), 0).edit();
        edit.putString(getString(R.string.saved_nome_utente), newNome);
        edit.commit();
        edit.putString(getString(R.string.saved_cognome_utente), newCognome);
        edit.commit();
        edit.putString(getString(R.string.saved_cellulare_utente), newCellulare);
        edit.commit();
        edit.putString(getString(R.string.saved_indirizzo_utente), newIndirizzo);
        edit.commit();
        edit.putString(getString(R.string.saved_bio_utente), newBio);
        edit.commit();

        setCampiConDatiUtente();
    }

    private void setCampiConDatiUtente(){
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

        if(context instanceof FragmentAListener){
            listener = (FragmentAListener) context;
        }else{
            throw new RuntimeException(context.toString() + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}