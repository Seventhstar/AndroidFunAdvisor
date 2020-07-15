package com.example.funadvisor.ui.Quote;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.funadvisor.MainActivity;
import com.example.funadvisor.Models.Quote;
import com.example.funadvisor.Network.GetQuoteTask;
import com.example.funadvisor.R;
import com.example.funadvisor.service.RestApiService;
import com.example.funadvisor.service.RetrofitInstance;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteFragment extends Fragment {

    private QuoteViewModel mViewModel;
    View root;
    MainActivity mainActivity;

//    @BindView(R.id.text_quote)
    TextView body;
    TextView author;

//    @BindView(R.id.button_get_quote)
    Button btn;

    GetQuoteTask.Listener thisListener;
    Context thisActivity;
    private QuoteViewModel notificationsViewModel;
    RestApiService apiService = RetrofitInstance.getApiService();

    public static QuoteFragment newInstance() {
        return new QuoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);

        root = inflater.inflate(R.layout.fragment_quote, container, false);
        thisListener = (GetQuoteTask.Listener) getActivity();
        thisActivity = getActivity();// getActivity();
//        mainActivity = ((MainActivity) getActivity());

        body = root.findViewById(R.id.text_quote);
        author = root.findViewById(R.id.quote_author);
        btn = root.findViewById(R.id.button_get_quote);
        mViewModel = new QuoteViewModel();
//        mainActivity.setQuoteFragment(this);
        Call<Quote> call = apiService.getQuote();
        btn.setOnClickListener(view -> {
//            call.enqueue(new Callback<Quote>() {
//                @Override
//                public void onResponse(Call<Quote> call, Response<Quote> response) {
//                    String mQuote = response.body().getBody();
//                    if (mQuote != null) {
//                        body.setText(mQuote);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Quote> call, Throwable t) {
//                    int b = 0;
//                }
            mViewModel.getQuote().observe(mainActivity, quote -> {
                body.setText(quote.getBody());
                author.setText(quote.getAuthor());
            });
        });


        notificationsViewModel.getText().observe(getViewLifecycleOwner(), s -> body.setText(s));
        return root;
    }
}