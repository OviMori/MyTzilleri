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

        binding.textViewEsaurimentoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.recyclerViewInEsaurimentoHome.getVisibility() == View.GONE) {
                    binding.recyclerViewInEsaurimentoHome.setVisibility(View.VISIBLE);
                    binding.textViewEsaurimentoIcon.setBackgroundResource(R.drawable.drop_menu_arrow_down);
                } else {
                    binding.recyclerViewInEsaurimentoHome.setVisibility(View.GONE);
                    binding.textViewEsaurimentoIcon.setBackgroundResource(R.drawable.drop_menu_arrow_right);
                }
            }
        });


        /**
         * Gestisce il click che fa apparire/scomparire la RecyclerView dei messaggi dell utente
         * Viene gestita anche la modifica del verso della freccia
         */

        binding.textViewNuoviMessaggiIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.recyclerViewNuoviMessaggiHome.getVisibility() == View.GONE) {
                    binding.recyclerViewNuoviMessaggiHome.setVisibility(View.VISIBLE);
                    binding.textViewNuoviMessaggiIcon.setBackgroundResource(R.drawable.drop_menu_arrow_down);
                } else {
                    binding.recyclerViewNuoviMessaggiHome.setVisibility(View.GONE);
                    binding.textViewNuoviMessaggiIcon.setBackgroundResource(R.drawable.drop_menu_arrow_right);
                }
            }
        });


        return binding.getRoot();
    }
}
