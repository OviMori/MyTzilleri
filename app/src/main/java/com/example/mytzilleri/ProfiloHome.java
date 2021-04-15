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

import org.jetbrains.annotations.NotNull;

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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false); //MODIFICA: Salvato il valore di ritorno in "View v"

        viewPager = binding.viewPager;
        tabLayout = binding.tabController;
        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new TabViewMessaggiFrag(), "Messaggi");
        adapter.addFragment(new TabViewPersonaleFrag(), "Personale");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        return binding.getRoot();
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