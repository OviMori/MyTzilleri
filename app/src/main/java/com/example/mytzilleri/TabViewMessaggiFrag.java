package com.example.mytzilleri;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.mytzilleri.databinding.FragmentProfiloMessaggiBinding;

public class TabViewMessaggiFrag extends Fragment {
    private FragmentProfiloMessaggiBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profilo_messaggi, container, false);

        /**
         * Gestisce il click che fa apparire/scomparire la RecyclerView dei prodotti in esaurimento
         * Viene gestita anche la modifica del verso della freccia
         */
        /*
        linearLayoutInEsaurimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inEsaurimentoRecycler.getVisibility() == View.GONE) {
                    inEsaurimentoRecycler.setVisibility(View.VISIBLE);
                    inEsaurimentoArrowHome.setBackgroundResource(R.drawable.drop_menu_arrow_down);
                } else {
                    inEsaurimentoRecycler.setVisibility(View.GONE);
                    inEsaurimentoArrowHome.setBackgroundResource(R.drawable.drop_menu_arrow_right);
                }
            }
        });
        */

        /**
         * Gestisce il click che fa apparire/scomparire la RecyclerView dei messaggi dell utente
         * Viene gestita anche la modifica del verso della freccia
         */
        /*
        linearLayoutNuoviMessaggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nuoviMessaggiRecycler.getVisibility() == View.GONE) {
                    nuoviMessaggiRecycler.setVisibility(View.VISIBLE);
                    nuoviMessaggiArrowHome.setBackgroundResource(R.drawable.drop_menu_arrow_down);
                } else {
                    nuoviMessaggiRecycler.setVisibility(View.GONE);
                    nuoviMessaggiArrowHome.setBackgroundResource(R.drawable.drop_menu_arrow_right);
                }
            }
        });

        */
        return binding.getRoot();
    }
}
