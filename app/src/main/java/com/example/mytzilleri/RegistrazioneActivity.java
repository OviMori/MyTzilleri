package com.example.mytzilleri;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrazioneActivity extends AppCompatActivity {

    EditText nome, cognome, email, password, confermaPassword;
    Button registrazione;
    ImageButton backButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crea_nuovo_utente_layout);

        nome = findViewById(R.id.created_name);
        cognome = findViewById(R.id.created_sname);
        email = findViewById(R.id.created_email);
        password = findViewById(R.id.created_password);
        confermaPassword = findViewById(R.id.created_confirm_password);

        backButton = findViewById(R.id.back_button_registrazione);

        registrazione = findViewById(R.id.salva_credenziali_button);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrazioneActivity.super.onBackPressed();
            }
        });

        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chiamare le funzioni per il check dell input dell utente

                //controllo che password e conferma password siano corrette
                checkPassword(password.getText().toString(), confermaPassword.getText().toString());

                //salvataggio delle credenziali nei preferiti
                salvaCredenziali(nome.getText().toString(), cognome.getText().toString(), email.getText().toString(), password.getText().toString());

            }
        });




    }

    /**
     *
     * @param password
     * @param confirmPassword
     * @return true se le due string coincidono
     */
    private boolean checkPassword(String password, String confirmPassword){
        if(password.equals(confirmPassword)){
            return true;
        }else{
            return false;
        }
    }


    /**
     *  Salvataggio dei dati dell utente
     */
    @SuppressLint("ApplySharedPref")
    private void salvaCredenziali(String nome, String cognome, String email, String password){
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preference_file_key), 0).edit();
        editor.putString(getString(R.string.saved_nome_login), nome);
        editor.commit();

        editor.putString(getString(R.string.saved_cognome_login), cognome);
        editor.commit();

        editor.putString(getString(R.string.saved_email_login), email);
        editor.commit();

        editor.putString(getString(R.string.saved_password_login), password);
        editor.commit();
    }

}


















