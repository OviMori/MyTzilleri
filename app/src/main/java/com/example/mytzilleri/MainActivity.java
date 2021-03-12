package com.example.mytzilleri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements ProfiloFrag.FragmentAListener{

    private Fragment frgamentProfilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
        startActivity(intent);

        startMainFragment();

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