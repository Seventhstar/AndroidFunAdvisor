package com.example.funadvisor.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.funadvisor.Models.Quote;
import com.example.funadvisor.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class GetQuoteTask extends AsyncTask<Void, Void, Quote> {

    public static final String Quote_URL = "https://api.forismatic.com/"; //cash?offset=0&limit=3&mode=rest
    private final TaskCompleteNotify mCommand;
    private final Context context;

    public GetQuoteTask(Context context, TaskCompleteNotify command) {
        this.context = context;
        mCommand = command;
    }

    public interface Command {
        void execute();
    }

    public interface Listener {
        void onLoadFinished(Command command);
    }

    public interface DataApi {
        @GET("api/1.0/?method=getQuote&format=json")
        Call<Quote> getQuote();
    }

    @Override
    protected void onPostExecute(Quote Quote) {
        if (Quote != null) {
            mCommand.Quote = Quote;
            mCommand.execute();
        } else {
            mCommand.Quote = new Quote();
        }
    }

    public static class TaskCompleteNotify implements Command {
        private final Listener mListener;
        private Quote Quote;

        public TaskCompleteNotify(Listener listener) {
            mListener = listener;
        }

        @Override
        public void execute() {
            mListener.onLoadFinished(this);
        }

        public Quote getQuote() {
            return Quote;
        }
    }

    @Override
    protected Quote doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Quote_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataApi api = retrofit.create(DataApi.class);

        Call<Quote> call = api.getQuote();
        try {
            Response<Quote> response = call.execute();
            Quote Quotes = response.body();
            return Quotes;
        } catch (IOException e) {
            Toast.makeText(context, "data problem", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
