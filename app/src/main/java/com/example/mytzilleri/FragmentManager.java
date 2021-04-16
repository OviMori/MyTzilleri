package com.example.mytzilleri;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentManager extends AppCompatActivity implements FragmentHome.FragmentAListener {

    private Fragment fragmentProfilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_manager_layout);
        startMainFragment();
    }

    private void startMainFragment(){
        BottomNavigationView bottNavMenu = (BottomNavigationView) findViewById(R.id.bottNavMenu);
        bottNavMenu.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new FragmentHome()).commit();  //potrei ripetere l istruzione replace inserendo turri i fragment che voglio
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item){
            Fragment fragment = null;

            switch(item.getItemId()){
                case R.id.turniFrag:
                    fragment = new FragmentTurni();
                    break;

                case R.id.profiloFrag:
                    fragment = new FragmentHome();
                    break;

                case R.id.chatFrag:
                    fragment = new FragmentChat();
                    break;

                case R.id.magazzinoFrag:
                    fragment = new FragmentMagazzino();
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
