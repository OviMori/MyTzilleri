package com.example.mytzilleri.product;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.example.mytzilleri.R;
import com.example.mytzilleri.databinding.PaginaProdottoBinding;

import java.util.Objects;

public class PaginaProdotto extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private PaginaProdottoBinding binding;
    private static final int PICK_FROM_GALLERY = 1;
    ArrayAdapter<CharSequence> spinnerCatArray;


    Product infoprodotto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_prodotto);
        binding = DataBindingUtil.setContentView(this, R.layout.pagina_prodotto);

        spinnerCatArray = ArrayAdapter.createFromResource(this, R.array.spinner_categoria_array, android.R.layout.simple_spinner_item);
        spinnerCatArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  //specifichiamo il layout da usare quando appare la lista di scelte dello spinner
        binding.infoProdottoCategoriaSpinner.setAdapter(spinnerCatArray);   //associamo l array allo spinner
        binding.infoProdottoCategoriaSpinner.setOnItemSelectedListener(this);

        if(getIntent().getSerializableExtra("infoProdotto") == null){
            infoprodotto = new Product();
        }else{
            infoprodotto = (Product) getIntent().getSerializableExtra("infoProdotto");
        }
        initInfoProdotto(infoprodotto);

        /*buttons*/
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent backIntent = new Intent(PaginaProdotto.this, MagazzinoFrag.class);
                //startActivity(backIntent);
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });
        /**
         * Salva le modifiche del prodotto
         */
        binding.bottoneSalvaModifiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputControl()) {
                    saveData();
                    Toast.makeText(PaginaProdotto.this, "Salvataggio...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PaginaProdotto.this, "Salvataggio fallito, 1 o piu campi non sono corretti...", Toast.LENGTH_LONG).show();
                }
            }
        });


        binding.immagineProdotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageManager();
            }
        });
    }

    private void imageManager(){
        try {
            //controllo dei permessi necessari per accedere alla galleria
            if (ActivityCompat.checkSelfPermission(PaginaProdotto.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PaginaProdotto.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
            } else {
                selectImage(PaginaProdotto.this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData(){
        infoprodotto.setNomeProdotto(Objects.requireNonNull(binding.infoProdottoNomeProdotto.getEditText()).getText().toString());
        infoprodotto.setCategoria(Objects.requireNonNull(binding.infoProdottoCategoria.getEditText()).getText().toString());
        infoprodotto.setQuantita(Objects.requireNonNull(binding.infoProdottoQuantita.getEditText()).getText().toString());
        infoprodotto.setNotificaEsaurimentoScorte(Objects.requireNonNull(binding.infoProdottoLimiteScorte.getEditText()).getText().toString());
        infoprodotto.setNomeFornitore(Objects.requireNonNull(binding.nomeFornitore.getEditText()).getText().toString());
        infoprodotto.setEmailFornitore(Objects.requireNonNull(binding.emailFornitore.getEditText()).getText().toString());
        infoprodotto.setTelFornitore(binding.cellulareFornitore.getEditText().getText().toString());

        DataRepository.INSTANCE.saveProduct(infoprodotto);
    }

    private boolean inputControl() {
        String infoNomeProdotto = Objects.requireNonNull(binding.infoProdottoNomeProdotto.getEditText()).getText().toString();
        String infoCategoriaProdotto = Objects.requireNonNull(binding.infoProdottoCategoria.getEditText()).getText().toString();
        String infoQuantitaProdotto = Objects.requireNonNull(binding.infoProdottoQuantita.getEditText()).getText().toString();
        String infoLimiteProdotto = Objects.requireNonNull(binding.infoProdottoLimiteScorte.getEditText()).getText().toString();
        String infoNomeFornitore = Objects.requireNonNull(binding.nomeFornitore.getEditText()).getText().toString();
        String infoEmailFornitore = Objects.requireNonNull(binding.emailFornitore.getEditText()).getText().toString();
        String infoCellulareFornitore = Objects.requireNonNull(binding.cellulareFornitore.getEditText()).getText().toString();

        if(infoNomeProdotto.trim().equals("")){
            binding.infoProdottoNomeProdotto.setError("Name cannot be blank");
            return false;
        }
        if(infoCategoriaProdotto.trim().equals("")){
            binding.infoProdottoCategoria.setError("Categoria cannot be blank");
            return false;
        }
        if(infoQuantitaProdotto.trim().equals("")){
            binding.infoProdottoQuantita.setError("Quantita cannot be blank");
            return false;
        }
        if(infoLimiteProdotto.trim().equals("")){
            binding.infoProdottoLimiteScorte.setError("Limite cannot be blank");
            return false;
        }
        if(infoNomeFornitore.trim().equals("")){
            binding.nomeFornitore.setError("Name cannot be blank");
            return false;
        }
        if(infoEmailFornitore.trim().equals("") || !checkEmail(binding.emailFornitore.getEditText())){
            binding.emailFornitore.setError("Email cannot be blank");
            return false;
        }
        if(infoCellulareFornitore.trim().equals("")){
            binding.cellulareFornitore.setError("Cellulare cannot be blank");
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sceltaUtente = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), sceltaUtente, Toast.LENGTH_LONG).show();
        Objects.requireNonNull(binding.infoProdottoCategoria.getEditText()).setText(sceltaUtente);
        infoprodotto.setCategoria(sceltaUtente);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private boolean checkEmail(EditText email) {
        if (email == null) {
            return false;
        }
        if (email.getText().toString().length() == 0 || !email.getText().toString().contains("@")) {
            email.setError("Inserire una mail valida");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        //returnIntent.putExtra("passed_item", 3);
        returnIntent.putExtra("infoProdottoReturn", infoprodotto);
        // setResult(RESULT_OK);
        setResult(Activity.RESULT_OK, returnIntent); //By not passing the intent in the result, the calling activity will get null data.
        super.finish();
    }

    public void initInfoProdotto(Product product) {
        if(product != null){
            if (!product.getNomeProdotto().equals(" ")) {
                Objects.requireNonNull(binding.infoProdottoNomeProdotto.getEditText()).setText(product.getNomeProdotto());
                Objects.requireNonNull(binding.infoProdottoQuantita.getEditText()).setText(String.valueOf(product.getQuantita()));
                Objects.requireNonNull(binding.infoProdottoCategoria.getEditText()).setText(product.getCategoria());
                Objects.requireNonNull(binding.infoProdottoLimiteScorte.getEditText()).setText(String.valueOf(product.getNotificaEsaurimentoScorte()));
                Objects.requireNonNull(binding.nomeFornitore.getEditText()).setText(product.getNomeFornitore());
                Objects.requireNonNull(binding.emailFornitore.getEditText()).setText(product.getEmailFornitore());
                Objects.requireNonNull(binding.cellulareFornitore.getEditText()).setText(product.getTelFornitore());
            }
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
                        binding.immagineProdotto.setImageBitmap(selectedImage);
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
                                binding.immagineProdotto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
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
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


}
