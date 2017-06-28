package com.example.shagee.unipoint.RestClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostgrestClient {

    public static final String BASE_URL = "http://52.4.27.162:3000/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
