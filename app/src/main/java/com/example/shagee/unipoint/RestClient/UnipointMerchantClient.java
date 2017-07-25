package com.example.shagee.unipoint.RestClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnipointMerchantClient {

    public static final String BASE_URL = "http://192.168.8.101:8080/UnipointWebServiceMerchantRest/";
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
