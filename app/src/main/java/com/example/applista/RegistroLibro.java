package com.example.applista;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.applista.entidads.Libro;
import com.example.applista.factories.RetroFactory;
import com.example.applista.services.LibroServices;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class RegistroLibro extends AppCompatActivity {
    static  final int REQUEST_IMAGEN_CAPTURE = 1000;
    static  final int REQUEST_PICK_IMAGE =1001;
    static  final int REQUEST_CAMERA_PERMISSION = 100;

    ImageView editImagen;
    String sImage;
    EditText imagenUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_libro);

        Button btn = findViewById(R.id.btnGuardar);

        EditText editTitulo = findViewById(R.id.txtTitulo);
        EditText editResumen = findViewById(R.id.txtResumen);
        EditText editAutor= findViewById(R.id.txtAutor);
        EditText editFecha= findViewById(R.id.txtFechaPu);
        EditText editTienda1= findViewById(R.id.txtTda1);
        EditText editTienda2= findViewById(R.id.txtTda2);
        EditText editTienda3= findViewById(R.id.txtTda3);
         editImagen = findViewById(R.id.txtImagen);
         imagenUrl = findViewById(R.id.imagenUrl);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = RetroFactory.build();
                LibroServices service = retrofit.create(LibroServices.class);
                Libro libro = new Libro();
                libro.titulo = String.valueOf(editTitulo.getText());
                libro.resumen = String.valueOf(editResumen.getText());
                libro.autor= String.valueOf(editAutor.getText());
                libro.fecha_publicacion= String.valueOf(editFecha.getText());
                libro.tienda_1= String.valueOf(editTienda1.getText());
                libro.tienda_2= String.valueOf(editTienda2.getText());
                libro.tienda_3= String.valueOf(editTienda3.getText());
                libro.imagen = String.valueOf(imagenUrl.getText());


                Call<Libro> call = service.create(libro);
                call.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {


                        if (response.isSuccessful()){
                          Log.e("APP", new Gson().toJson(response.body()));

                      }
                    }
                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {

                    }
                });

            }
        });

        //click para obtner imagen de la camara
        editImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalleria();
            }
        });
    }


    // obtener imagen de la camara
    private void openGalleria(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccione imagent"), REQUEST_PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            editImagen.setImageBitmap(bitmap);
        }

        try {
            if (resultCode == RESULT_OK) {
                Uri path = data.getData();
                editImagen.setImageURI(path);

                final InputStream imageStream = getContentResolver().openInputStream(path);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                sImage = convertBase64(selectedImage);


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    private String convertBase64(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        sImage = Base64.encodeToString(b, Base64.DEFAULT);
        imagenUrl.setText(sImage);
        return sImage;
    }

}