package com.example.mytzilleri;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyAdminAccount();
        DataRepository.INSTANCE.dropAllProd();

        //test
        User testUser = new User();
        testUser.setTempAdmin();
        DataRepository.INSTANCE.salvaUtente(testUser);
        //test

        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
        startActivity(intent);
        finish();
    }

    private void verifyAdminAccount(){
        if(!com.example.mytzilleri.DataRepository.INSTANCE.adminExist()){   //if admin does not exist
            DataRepository.INSTANCE.createAdminAccount();
        }
    }


}