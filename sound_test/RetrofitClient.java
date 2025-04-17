package com.example.sound_test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String API_URL = "http://10.0.2.2:3000/";
    private static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    public static Retrofit getInstance() {
        return retrofit;
    }
}
