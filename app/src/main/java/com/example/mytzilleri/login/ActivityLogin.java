package com.example.mytzilleri.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mytzilleri.R;
import com.example.mytzilleri.databinding.LoginLayoutBinding;
import com.example.mytzilleri.fragment.FragmentManager;
import com.example.mytzilleri.product.DataRepository;

public class ActivityLogin extends AppCompatActivity {
    private LoginLayoutBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        binding = DataBindingUtil.setContentView(this, R.layout.login_layout);

        androidx.fragment.app.FragmentManager fragM = getSupportFragmentManager();
        PopUpErrorLogIn dialogErrorFrag = new PopUpErrorLogIn();

        checkCredenzialiGiaSalvate();   //riempimento automatico se le credenziali sono gia state salvate

        binding.edittextEmail.getEditText().setText("admin@");
        binding.edittextPassword.getEditText().setText("admin");


        binding.registratiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(ActivityLogin.this, RegistrazioneActivity.class), 0);
            }
        });

        binding.accediButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controllo credenziali
                if (controlloCredenziali(binding.edittextEmail.getEditText().getText().toString(), binding.edittextPassword.getEditText().getText().toString())) {   //se le credenziali sono corrette
                    Intent intent = new Intent(ActivityLogin.this, FragmentManager.class);
                    startActivity(intent);

                } else {
                    dialogErrorFrag.show(fragM, "Errore");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkCredenzialiGiaSalvate();   //riempimento automatico se le credenziali sono gia state salvate

    }

    private boolean checkCredenzialiGiaSalvate() {
        User user = DataRepository.INSTANCE.getCurrentUser(); //check if there is a current user already saved

        if (user.getEmail().equals("") || user.getPassword().equals("")) {
            return false;
        } else {
            binding.edittextEmail.getEditText().setText(user.getEmail());
            binding.edittextPassword.getEditText().setText(user.getPassword());
            return true;
        }
    }

    private boolean controlloCredenziali(String email, String password) {
        if (DataRepository.INSTANCE.userExist(email)) {   //if user exist
            User user = DataRepository.INSTANCE.getUser(email);
            if(!user.getEmail().equals("")){   //if is not empty
                if (user.getPassword().equals(password)) {
                    DataRepository.INSTANCE.salvaUtenteCorrente(user);
                    return true;
                } else {
                    binding.edittextEmail.getEditText().setError("Le credenziali non sono corrette");
                    return false;
                }
            }

        }
        return false;
    }
}

























