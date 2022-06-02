package com.example.applista.services;
import retrofit2.Call;
import com.example.applista.entidads.Libro;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LibroServices {

    @GET("libro")
    Call<List<Libro>> getLibro();



     //buscar, obtener un conctacto
    @GET("libro/{id}")
    Call<Libro> findMusica(@Path("id") int id);

    //Guardar
    @POST("libro")
    Call<Libro> create(@Body Libro libro);

    //editar
    @PUT("libro/{id}")
    Call<Libro> editar(@Path("id") int id, @Body Libro libro);
  //ELIMINAR
    @DELETE("libro/{id}")
    Call<Libro> eliminar(@Path("id") int id);


}
