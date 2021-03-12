package com.example.mytzilleri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LogInActivity extends AppCompatActivity implements ProfiloFrag.FragmentAListener{

    EditText editUsername, editPassword;
    Button accedi, registrati;
    String username, password;
    private Fragment frgamentProfilo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        editUsername = findViewById(R.id.edittext_username);
        editPassword = findViewById(R.id.edittext_password);
        accedi = findViewById(R.id.accedi_button);

        username = editUsername.getText().toString();
        password = editPassword.getText().toString();
        registrati = findViewById(R.id.registrati_button);

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegistrazioneActivity.class);
                startActivity(intent);
            }
        });

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //controllo credenziali
                if(controlloCredenziali(username, password)){   //se le credenziali sono corrette
                    startMainFragment();

                }else{
                    //implementare un toastMessage o floatingPopUp per avvisare l utente che le credenziali sono errate
                }
            }
        });



    }

    private boolean controlloCredenziali(String email, String password){
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_key), 0);
        String savedUsername = pref.getString(getString(R.string.saved_email_login), "");
        String savedPassword = pref.getString(getString(R.string.saved_password_login), "");

        if(savedPassword.equals("") || savedUsername.equals("")){
            return false;
        }

        if(savedUsername.equals(username) && savedPassword.equals(password)){
            return true;
        }else{
            return false;
        }
    }




    private void startMainFragment(){
        BottomNavigationView bottNavMenu = (BottomNavigationView) findViewById(R.id.bottNavMenu);
        bottNavMenu.setOnNavigationItemSelectedListener(navListener);

        frgamentProfilo = new ProfiloFrag();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ProfiloFrag()).commit();  //potrei ripetere l istruzione replace inserendo turri i fragment che voglio
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item){
            Fragment fragment = null;

            switch(item.getItemId()){
                case R.id.turniFrag:
                    fragment = new TurniFrag();
                    break;

                case R.id.profiloFrag:
                    fragment = new ProfiloFrag();
                    break;

                case R.id.chatFrag:
                    fragment = new ChatFrag();
                    break;

                case R.id.magazzinoFrag:
                    fragment = new MagazzinoFrag();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();

            return true;
        }
    };

    @Override
    public void onInputASent(CharSequence input) {

    }
}

























