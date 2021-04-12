package com.example.mytzilleri;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


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
        if(!com.example.mytzilleri.DataRepository.INSTANCE.adminExist()){   //if admin does not exist
            DataRepository.INSTANCE.createAdminAccount();
        }
    }


}