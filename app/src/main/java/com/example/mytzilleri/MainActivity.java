package com.example.mytzilleri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private Fragment frgamentProfilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyAdminAccount();

        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
        startActivity(intent);
    }

    private void verifyAdminAccount(){
        if(!DataRepository.INSTANCE.adminExist()){   //if admin does not exist
            DataRepository.INSTANCE.createAdminAccount();
        }
    }


}