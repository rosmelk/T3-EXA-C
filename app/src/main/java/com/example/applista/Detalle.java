package com.example.applista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applista.entidads.Libro;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class Detalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        String musicJSON = getIntent().getStringExtra("DATOS");
        Libro libro = new Gson().fromJson(musicJSON, Libro.class);

        ImageView ivAvatar = findViewById(R.id.tvimagen);
        TextView  ivTitulo = findViewById(R.id.tvTitulo);
        TextView  ivResumen = findViewById(R.id.tvResumen);
        TextView  ivAutor = findViewById(R.id.tvAutor);
        TextView  ivFecha = findViewById(R.id.tvfecha);
        TextView  ivTienda1 = findViewById(R.id.tvtd1);
        TextView  ivTienda2 = findViewById(R.id.tvtd2);
        TextView  ivTienda3 = findViewById(R.id.tvtd3);

        Picasso.get().load(libro.getImagen()).into(ivAvatar);


        ivTitulo.setText(libro.titulo);
        ivResumen.setText(libro.resumen);
        ivAutor.setText(libro.autor);
        ivFecha.setText(libro.fecha_publicacion);
        ivTienda1.setText(libro.tienda_1);
       ivTienda2.setText(libro.tienda_2);
        ivTienda3.setText(libro.tienda_3);


    }
}