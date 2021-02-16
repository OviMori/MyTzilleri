package com.example.mytzilleri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottNavMenu = (BottomNavigationView) findViewById(R.id.bottNavMenu);
        bottNavMenu.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ProfiloFrag()).commit();

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
}