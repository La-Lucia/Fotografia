package com.example.fotografia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Detalles;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap bitmap;
    DatabaseHandler objectDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            Detalles=findViewById(R.id.TxtDescripcion);
            imageView = findViewById(R.id.img);

            objectDatabaseHandler = new DatabaseHandler(this);

        }catch (Exception e){

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    public void SeleccionarImagen(View objectView){

        try {

            Intent intent = new Intent();
            intent.setType("image/*");

            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);

        }catch (Exception e){

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
                imageFilePath=data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                imageView.setImageBitmap(bitmap);
            }

        }catch (Exception e){

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    public void guardarImagen(View view){

        try{

            if (!Detalles.getText().toString().isEmpty() && imageView.getDrawable()!=null && bitmap!=null){

                objectDatabaseHandler.GuardarImagen(new Photograph(Detalles.getText().toString(), bitmap));

            }else{

                Toast.makeText(this, "Por favor seleccione una imagen", Toast.LENGTH_SHORT).show();

            }

        }catch (Exception e){


        }

    }

    public void moveToShowActivity(View view){

        startActivity(new Intent(this, ListaImagenes.class));

    }

}