package com.example.applista.adapters;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applista.Detalle;
import com.example.applista.R;
import com.example.applista.entidads.Libro;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.util.List;

public class AdapterMusica extends RecyclerView.Adapter <AdapterMusica.VieweHolderMusica> {

    List<Libro> libro;
    public AdapterMusica(List<Libro> libro) {
        this.libro = libro;
    }



    @NonNull
    @Override
    public AdapterMusica.VieweHolderMusica onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_layout, parent, false);
        return new VieweHolderMusica(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VieweHolderMusica vh, int position) {
          View itemView =vh.itemView;

          Libro libr = libro.get(position);
        TextView tTitulo = itemView.findViewById(R.id.tTitulo);
        ImageView tImagen = itemView.findViewById(R.id.tImagen);

        Picasso.get().load(libr.getImagen()).into(tImagen);
        tTitulo.setText(libr.titulo);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 Intent  intent = new Intent(itemView.getContext(), Detalle.class);
                 String musicJSON = new Gson().toJson(libr);
                 intent.putExtra("DATOS", musicJSON);
                itemView.getContext().startActivity(intent);

            }
        });



/*
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://628ea9a0dc4785236532df6c.mockapi.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MusicaServices service = retrofit.create(MusicaServices.class);
                Call<Musica> call = service.findMusica(music.id);

                call.enqueue(new Callback<Musica>() {
                    @Override
                    public void onResponse(Call<Musica> call, Response<Musica> response) {
                        if (! response.isSuccessful()){
                            Log.e("APP_VJ20202", "Error de aplicación");
                        }
                        else{
                            Log.i("APP_VJ20202", "Respuesta Correcta");
                            Log.i("APP_VJ20202", new Gson().toJson(response.body()));
                        }
                    }
                    @Override
                    public void onFailure(Call<Musica> call, Throwable t) {
                        Log.e("APP_VJ20202", "No hubo conectividad con el servicio web");
                    }
                });

            }
        });*/

    }

/*    private  void removeItem(int position){
       Libro music = libro.get(position);
       Retrofit retrofit = RetroFactory.build();
       LibroServices services =retrofit.create(LibroServices.class);
      // Call<Libro> call = services.eliminar(music.id);
       call.enqueue(new Callback<Libro>() {
           @Override
           public void onResponse(Call<Libro> call, Response<Libro> response) {
               Log.i("APP","sE ELIMINO CORRECTAMENTE" + music.nombreCancion);
               libro.remove(position);
               notifyItemRemoved(position);
               notifyItemRangeChanged(position, libro.size());
           }

           @Override
           public void onFailure(Call<Libro> call, Throwable t) {

           }
       });

   }*/



    @Override
    public int getItemCount() {
        return libro.size();
    }

    public class VieweHolderMusica extends RecyclerView.ViewHolder {
        public VieweHolderMusica(@NonNull View itemView) {
            super(itemView);
        }
    }
}
