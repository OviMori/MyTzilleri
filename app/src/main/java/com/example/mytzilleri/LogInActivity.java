package com.example.mytzilleri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mytzilleri.databinding.LoginLayoutBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LogInActivity extends AppCompatActivity {
    private LoginLayoutBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        binding = DataBindingUtil.setContentView(this, R.layout.login_layout);

        FragmentManager fragM = getSupportFragmentManager();
        PopUpErrorLogIn dialogErrorFrag = new PopUpErrorLogIn();

        checkCredenzialiGiaSalvate();   //riempimento automatico se le credenziali sono gia state salvate


        binding.registratiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LogInActivity.this, RegistrazioneActivity.class);
                //startActivity(intent);

                startActivityForResult(new Intent(LogInActivity.this, RegistrazioneActivity.class), 0);
            }
        });

        binding.accediButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controllo credenziali
                if (controlloCredenziali(binding.edittextEmail.getEditText().getText().toString(), binding.edittextPassword.getEditText().getText().toString())) {   //se le credenziali sono corrette
                    Intent intent = new Intent(LogInActivity.this, ManagerFragment.class);
                    startActivity(intent);

                } else {
                    dialogErrorFrag.show(fragM, "Errore");
                }
            }
        });
    }

    private boolean checkCredenzialiGiaSalvate() {
        Utente user = DataRepository.INSTANCE.getCurrentUser(); //check if there is a current user already saved

        if (user.getEmail().equals("") || user.getPassword().equals("")) {
            return false;
        } else {
            binding.edittextEmail.getEditText().setText(user.getEmail());
            binding.edittextPassword.getEditText().setText(user.getPassword());
            return true;
        }
    }

    private boolean controlloCredenziali(String email, String password) {
        if (DataRepository.INSTANCE.userExist(email)) {   //if user exist
            Utente user = DataRepository.INSTANCE.getUser(email);
            if (user.getPassword().equals(password)) {
                return true;
            } else {
                binding.edittextEmail.getEditText().setError("Le credenziali non sono corrette");
                return false;
            }
        }
        return false;
    }
}

























