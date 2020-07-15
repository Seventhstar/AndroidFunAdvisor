package com.example.funadvisor.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteRetrofitInstance {
    private static Retrofit retrofit = null;
    public static final String QUOTE_BASE_URL = "https://api.forismatic.com/api/1.0/";

    public static QuoteRestApiService getApiService() {
        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(QUOTE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(QuoteRestApiService.class);
    }

}
