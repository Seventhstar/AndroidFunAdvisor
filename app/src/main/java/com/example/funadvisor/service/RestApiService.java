package com.example.funadvisor.service;

import com.example.funadvisor.Models.Quote;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RestApiService {
    @GET("?method=getQuote&format=json")
    Call<Quote> getQuote();
}
