package com.example.mytzilleri;

import android.annotation.SuppressLint;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class PaginaProdotto extends AppCompatActivity {

    SwitchCompat mySwitch;
    ImageView imagineProdotto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_prodotto);

        mySwitch = findViewById(R.id.switch_notifica_esaurimento);
        imagineProdotto = findViewById(R.id.image_prodotto);

        imagineProdotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //procedura per cambiare immagine del prodotto
            }
        });


        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

    }
}
