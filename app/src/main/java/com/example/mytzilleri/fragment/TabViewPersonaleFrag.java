package com.example.mytzilleri.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.mytzilleri.R;
import com.example.mytzilleri.login.User;
import com.example.mytzilleri.databinding.FragmentProfiloAccountBinding;
import com.example.mytzilleri.product.DataRepository;

public class TabViewPersonaleFrag extends Fragment {
    private FragmentProfiloAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profilo_account, container, false);

        setCampiConDatiUtente();

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
        String newCognome = binding.infoUtenteCognome.getEditText().getText().toString();
        String newPassword = binding.infoUtentePassword.getEditText().getText().toString();
        String newEmail = binding.infoUtenteEmail.getEditText().getText().toString();
        String newBio = binding.infoUtenteBio.getEditText().getText().toString();

        User newUser = new User(newNome, newCognome,newEmail, newPassword, newBio);
        DataRepository.INSTANCE.salvaUtenteCorrente(newUser);
        DataRepository.INSTANCE.salvaUtente(newUser);
        //aggiornare sia la lista degli utenti che i dati dell utente corrente
        setCampiConDatiUtente();
    }

    private void setCampiConDatiUtente() {
        SharedPreferences pref = this.getActivity().getSharedPreferences(getString(R.string.preference_file_key), 0);

        User currentUser = DataRepository.INSTANCE.getCurrentUser();

        if(!currentUser.getEmail().equals("")) {
            binding.infoUtenteNome.getEditText().setText(currentUser.getNome());
            binding.infoUtenteCognome.getEditText().setText(currentUser.getCognome());
            binding.infoUtenteEmail.getEditText().setText(currentUser.getEmail());
            binding.infoUtentePassword.getEditText().setText(currentUser.getPassword());
            binding.infoUtenteBio.getEditText().setText(currentUser.getBio());
        }
    }
}