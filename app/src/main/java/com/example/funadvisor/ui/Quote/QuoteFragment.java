package com.example.funadvisor.ui.Quote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.funadvisor.Models.Quote;
import com.example.funadvisor.R;
import com.example.funadvisor.service.QuoteRestApiService;
import com.example.funadvisor.service.QuoteRetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteFragment extends Fragment {
    View root;
    Button btn;
    TextView body;
    TextView author;

    QuoteRestApiService quoteApiService = QuoteRetrofitInstance.getApiService();

    public static QuoteFragment newInstance() {
        return new QuoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_quote, container, false);

        body = root.findViewById(R.id.text_quote);
        author = root.findViewById(R.id.quote_author);
        btn = root.findViewById(R.id.button_get_quote);

        btn.setOnClickListener(view -> {
            Call<Quote> call = quoteApiService.getQuote();
            call.enqueue(new Callback<Quote>() {
                @Override
                public void onResponse(Call<Quote> call, Response<Quote> response) {
                    Quote mQuote = response.body();
                    if (mQuote != null) {
                        body.setText(mQuote.getBody());
                        author.setText(mQuote.getAuthor());
                    }
                }

                @Override
                public void onFailure(Call<Quote> call, Throwable t) {
                    body.setText(t.getMessage());
                    author.setText("Error");
                }
            });
        });

        return root;
    }
}