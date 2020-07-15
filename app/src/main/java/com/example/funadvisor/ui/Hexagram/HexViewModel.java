package com.example.funadvisor.ui.Hexagram;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HexViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HexViewModel() {
        mText = new MutableLiveData<>();

        mText.setValue("Книга перемен – Ицзин");
    }

    public LiveData<String> getText() {
        return mText;
    }
}