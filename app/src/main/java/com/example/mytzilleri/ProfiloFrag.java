package com.example.mytzilleri;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

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
    FrameLayout framePromemoriaUtente;
    Button buttonTest;
    private FragmentAListener listener; //questo listener aspetta che l activity utilizzi il nostro fragment
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

        framePromemoriaUtente = v.findViewById(R.id.promemoria_utente);
        buttonTest = v.findViewById(R.id.buttonTest);

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(framePromemoriaUtente.getVisibility() == View.GONE){
                    framePromemoriaUtente.setVisibility(View.VISIBLE);
                }else{
                    framePromemoriaUtente.setVisibility(View.GONE);
                }
            }
        });

        return v;
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