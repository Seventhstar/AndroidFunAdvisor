package com.example.funadvisor.ui.Quote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.funadvisor.Models.Quote;
import com.example.funadvisor.Models.QuoteRepository;


public class QuoteViewModel extends ViewModel {
    //implements GetQuoteTask.Listener
    private MutableLiveData<String> mText;
    private QuoteRepository quoteRepository;

    public QuoteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Здесь будет ответ");
        quoteRepository = new QuoteRepository();
        getQuote();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText( MutableLiveData<String> newText) {
        mText = newText;
    }

//    @Override
//    public void onLoadFinished(GetQuoteTask.Command command) {
//        if (command instanceof GetQuoteTask.TaskCompleteNotify) {
//            Quote quote = ((GetQuoteTask.TaskCompleteNotify) command).getQuote();
//
////            mText.setValue(quote.getBody());
////            author.setText(Quotes.getAuthor());
//
//            //movieAdapter.refresh((;
//        }
//    }

    public LiveData<Quote> getQuote() {
        return quoteRepository.getMutableLiveData();
    }
}