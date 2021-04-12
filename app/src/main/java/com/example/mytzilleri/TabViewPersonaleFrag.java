package com.example.mytzilleri;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.mytzilleri.databinding.FragmentProfiloAccountBinding;

public class TabViewPersonaleFrag extends Fragment {
    private FragmentProfiloAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profilo_account, container, false);

        binding.buttonSalvaDatiUtente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggiornaDatiUtente();
            }
        });

        return binding.getRoot();
    }


    private void aggiornaDatiUtente() {
        String newNome = binding.infoUtenteNome.getEditText().getText().toString();
        String newCognome = binding.infoUtenteNome.getEditText().getText().toString();
        String newCellulare = binding.infoUtenteNome.getEditText().getText().toString();
        String newIndirizzo = binding.infoUtenteNome.getEditText().getText().toString();
        String newBio = binding.infoUtenteNome.getEditText().getText().toString();

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
    }


}