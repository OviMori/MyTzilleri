package com.example.mytzilleri;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManagerFragment extends AppCompatActivity implements ProfiloHome.FragmentAListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_manager_layout);
        startMainFragment();
    }

    private void startMainFragment(){
        BottomNavigationView bottNavMenu = (BottomNavigationView) findViewById(R.id.bottNavMenu);
        bottNavMenu.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ProfiloHome()).commit();  //potrei ripetere l istruzione replace inserendo turri i fragment che voglio
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
                    fragment = new ProfiloHome();
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
