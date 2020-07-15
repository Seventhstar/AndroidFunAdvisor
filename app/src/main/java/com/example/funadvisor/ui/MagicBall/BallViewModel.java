package com.example.funadvisor.ui.MagicBall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BallViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BallViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Здесь будет ответ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}