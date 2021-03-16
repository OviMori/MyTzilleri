package com.example.mytzilleri;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.Serializable;

public class PaginaProdotto extends AppCompatActivity {

    private static final int PICK_FROM_GALLERY = 1;
    SwitchCompat mySwitch;
    ImageView immagineProdotto, back_button;
    EditText nomeProdotto, categoria, quantita, limiteScorte, nomeFornitore, emailFornitore, telFornitore;
    Button salvaModoficheProdotto;

    Prodotto infoprodotto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_prodotto);

        mySwitch = findViewById(R.id.switch_notifica_esaurimento);
        immagineProdotto = findViewById(R.id.immagine_prodotto);
        back_button = findViewById(R.id.back_button);
        salvaModoficheProdotto = findViewById(R.id.bottone_salva_modifiche);

        nomeProdotto = findViewById(R.id.info_prodotto_nome_prodotto);
        categoria = findViewById(R.id.info_prodotto_categoria);
        quantita = findViewById(R.id.info_prodotto_quantita);
        limiteScorte = findViewById(R.id.info_prodotto_esaurimento_edit);
        nomeFornitore = findViewById(R.id.nome_fornitore);
        emailFornitore = findViewById(R.id.email_fornitore);
        telFornitore = findViewById(R.id.cellulare_fornitore);

        infoprodotto = (Prodotto) getIntent().getSerializableExtra("infoProdotto");
        initInfoProdotto(infoprodotto);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaginaProdotto.super.onBackPressed();
            }
        });
        /**
         * Salva le modifiche del prodotto
         */
        salvaModoficheProdotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkEmail(emailFornitore)){
                    if(nomeProdotto.getText().toString() == null){
                        nomeProdotto.setText(" ");
                        Log.i("Salvataggio--- textBox == null, scrive spazio","");
                    }else{
                        infoprodotto.setNomeProdotto(nomeProdotto.getText().toString());
                        Log.i("TextBox nome == ", nomeProdotto.getText().toString());
                        Log.i("Salvataggio--- textBox != null, salva valore","");
                    }

                    if(categoria.getText().toString() == null){
                        categoria.setText(" ");
                    }else{
                        infoprodotto.setCategoria(categoria.getText().toString());
                    }

                    if(quantita.getText().toString() == null){
                        quantita.setText("0");
                    }else{
                        infoprodotto.setQuantita(Integer.parseInt(quantita.getText().toString()));
                    }

                    if(limiteScorte.getText().toString() == null){
                        limiteScorte.setText("0");
                    }else{
                        infoprodotto.setNotificaEsaurimentoScorte(Integer.parseInt(limiteScorte.getText().toString()));
                    }

                    if(nomeFornitore.getText().toString() == null){
                        nomeFornitore.setText(" ");
                    }else{
                        infoprodotto.setNomeFornitore(nomeFornitore.getText().toString());
                    }

                    if(emailFornitore.getText().toString() == null){
                        emailFornitore.setText(" ");
                    }else{
                        infoprodotto.setEmailFornitore(emailFornitore.getText().toString());
                    }

                    if(telFornitore.getText().toString() == null){
                        telFornitore.setText("0");
                    }else{
                        infoprodotto.setTelFornitore(telFornitore.getText().toString());
                    }

                    Toast.makeText(PaginaProdotto.this, "Salvataggio...", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(PaginaProdotto.this, "Salvataggio fallito, 1 o piu campi non sono corretti...", Toast.LENGTH_LONG).show();
                }


            }
        });



        immagineProdotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //procedura per cambiare immagine del prodotto

                try {
                    //controllo dei permessi necessari per accedere alla galleria
                    if (ActivityCompat.checkSelfPermission(PaginaProdotto.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(PaginaProdotto.this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {

                        selectImage(PaginaProdotto.this);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

    private boolean checkEmail(EditText email){
        if(email.getText().toString().length() == 0 || !email.getText().toString().contains("@")){
            email.setError("Inserire una mail valida");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }

    @Override
    public void finish() {
        Log.d("onNOteCLick-------------------------------", "6666666666666666666666666666666666666666666666666666" );
        Intent returnIntent = new Intent();
        //returnIntent.putExtra("passed_item", 3);
        returnIntent.putExtra("infoProdottoReturn", infoprodotto);
        // setResult(RESULT_OK);
        setResult(Activity.RESULT_OK, returnIntent); //By not passing the intent in the result, the calling activity will get null data.
        super.finish();
    }

    public void  initInfoProdotto(Prodotto prodotto){
        if(!prodotto.getNomeProdotto().equals(" ")){
            nomeProdotto.setText(prodotto.getNomeProdotto());
            categoria.setText(prodotto.getCategoria());
            quantita.setText(String.valueOf(prodotto.getQuantita()));
            limiteScorte.setText(String.valueOf(prodotto.getNotificaEsaurimentoScorte()));
            nomeFornitore.setText(prodotto.getNomeFornitore());
            emailFornitore.setText(prodotto.getEmailFornitore());
            telFornitore.setText(prodotto.getTelFornitore());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        immagineProdotto.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                immagineProdotto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                //cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }


    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



}
