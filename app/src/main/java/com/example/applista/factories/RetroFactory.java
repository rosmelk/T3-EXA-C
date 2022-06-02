package com.example.applista.factories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFactory {
    public static Retrofit build(){

        return new Retrofit.Builder()
                .baseUrl("https://628f371bdc478523653bc250.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
