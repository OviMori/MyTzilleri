package com.example.mytzilleri;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import com.example.mytzilleri.databinding.CreaNuovoUtenteLayoutBinding;

public class RegistrazioneActivity extends AppCompatActivity {
    private CreaNuovoUtenteLayoutBinding  binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crea_nuovo_utente_layout);

        binding = DataBindingUtil.setContentView(this, R.layout.crea_nuovo_utente_layout);



        /**
         * tasto indietro tramite l icona del layout
         */
        binding.backButtonRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrazioneActivity.super.onBackPressed();
            }
        });

        /**
         * pulsante per il controllo e salvataggio dei dati
         */
        binding.salvaCredenzialiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chiamare le funzioni per il check dell input dell utente

                //controllo che password, conferma password e email siano corrette
                if(checkPassword(
                        binding.createdPassword.getEditText().getText().toString(), binding.createdConfirmPassword.getEditText().getText().toString())
                        && checkEmail(binding.createdEmail.getEditText().getText().toString())){
                    //salvataggio delle credenziali nei preferiti
                    salvaCredenziali(
                            binding.createdName.getEditText().getText().toString(),
                            binding.createdSname.getEditText().getText().toString(),
                            binding.createdEmail.getEditText().getText().toString(),
                            binding.createdPassword.getEditText().getText().toString());
                    RegistrazioneActivity.super.onBackPressed();

                }
            }
        });

    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("passed_item", -1);
        // setResult(RESULT_OK);
        setResult(RESULT_OK, returnIntent); //By not passing the intent in the result, the calling activity will get null data.
        super.finish();
    }

    private boolean checkEmail(String email){
        if(email.length() == 0 || !email.contains("@")){
            binding.createdEmail.setError("Inserire una mail valida");
            return false;
        }else{
            binding.createdEmail.setError(null);
            return true;
        }
    }

    /**
     *
     * @param password
     * @param confirmPassword
     * @return true se le due string coincidono
     */
    private boolean checkPassword(String password, String confirmPassword){

        if(password.length() == 0
                || confirmPassword.length() == 0
                || !password.equals(confirmPassword)){
            binding.createdPassword.setError("Le password inserite non corrispondono");
            return false;
        }else{
            binding.createdPassword.setError(null);
            return true;
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

        Log.i("nome", nome);

        editor.putString(getString(R.string.saved_nome_utente), nome);
        editor.commit();

        editor.putString(getString(R.string.saved_cognome_utente), cognome);
        editor.commit();

        Log.i("cognome", cognome);

        editor.putString(getString(R.string.saved_email_login), email);
        editor.commit();

        editor.putString(getString(R.string.saved_indirizzo_utente), email);
        editor.commit();

        Log.i("email", email);

        editor.putString(getString(R.string.saved_password_login), password);
        editor.commit();

        Log.i("password", password);

        Toast.makeText(RegistrazioneActivity.this, "Credenziali salvate", Toast.LENGTH_LONG).show();
    }

}


















