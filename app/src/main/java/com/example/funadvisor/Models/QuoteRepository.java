package com.example.funadvisor.Models;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.funadvisor.service.RestApiService;
import com.example.funadvisor.service.RetrofitInstance;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteRepository {
    private Quote mQuote;
    private MutableLiveData<Quote> mutableLiveData = new MutableLiveData<>();

    public QuoteRepository() {
    }

    public MutableLiveData<Quote> getMutableLiveData() {
        RestApiService apiService = RetrofitInstance.getApiService();

        Call<Quote> call = apiService.getQuote();
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                mQuote = response.body();
                if (mQuote != null) {
                    mutableLiveData.setValue(mQuote);
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Quote quote = new Quote();
                quote.setBody(t.getMessage());
                quote.setAuthor("error");
            }
        });

        return mutableLiveData;
    }

}
