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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LogInActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button accedi, registrati;
    String email, password;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        FragmentManager fragM = getSupportFragmentManager();
        PopUpErrorLogIn dialogErrorFrag = new PopUpErrorLogIn();

        editEmail = findViewById(R.id.edittext_email);
        editPassword = findViewById(R.id.edittext_password);
        accedi = findViewById(R.id.accedi_button);
        registrati = findViewById(R.id.registrati_button);


        checkCredenzialiGiaSalvate();   //riempimento automatico se le credenziali sono gia state salvate

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LogInActivity.this, RegistrazioneActivity.class);
                //startActivity(intent);

                startActivityForResult(new Intent(LogInActivity.this, RegistrazioneActivity.class), 0);
            }
        });

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controllo credenziali
                if(controlloCredenziali(editEmail.getText().toString(), editPassword.getText().toString())){   //se le credenziali sono corrette
                    LogInActivity.super.onBackPressed();

                } else{
                    dialogErrorFrag.show(fragM, "Errore");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkCredenzialiGiaSalvate();   //riempimento automatico se le credenziali sono gia state salvate

    }

    private boolean checkCredenzialiGiaSalvate(){
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_key), 0);
        String savedEmail = pref.getString(getString(R.string.saved_email_login), "");
        String savedPassword = pref.getString(getString(R.string.saved_password_login), "");

        if(savedEmail.equals("") || savedPassword.equals("")){
            return false;
        }else{
            editEmail.setText(savedEmail);
            editPassword.setText(savedPassword);
            return true;
        }
    }

    private boolean controlloCredenziali(String email, String password){
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_key), 0);
        String savedEmail = pref.getString(getString(R.string.saved_email_login), "");
        String savedPassword = pref.getString(getString(R.string.saved_password_login), "");

        if(savedPassword.equals("") || savedEmail.equals("")){
            return false;
        }

        if(savedEmail.equals(email) && savedPassword.equals(password)){
            return true;
        }else{
            return false;
        }
    }


}

























