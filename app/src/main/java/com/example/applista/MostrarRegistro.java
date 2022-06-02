package com.example.applista;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import retrofit2.Call;
import com.example.applista.adapters.AdapterMusica;
import com.example.applista.entidads.Libro;
import com.example.applista.factories.RetroFactory;
import com.example.applista.services.LibroServices;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MostrarRegistro extends AppCompatActivity {

    List<Libro> libro = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registro);
        Retrofit retrofit = RetroFactory.build();
        LibroServices service = retrofit.create(LibroServices.class);
        Call<List<Libro>> call = service.getLibro();

        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {

                    libro = response.body();
                    AdapterMusica adapter = new AdapterMusica(libro);
                    RecyclerView rv = findViewById(R.id.rvMusica);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e("APP_VJ20202", "No hubo conectividad con el servicio web");
            }
        });

    }


}