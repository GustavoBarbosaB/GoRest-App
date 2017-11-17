package com.example.gustavobarbosa.gorest.Rest;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Gustavo Barbosa on 29/10/2017.
 */

public class NationApi {
    public static final String BASE_URL = "https://restcountries.eu/rest/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getNation() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


