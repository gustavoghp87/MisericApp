package com.paquete.misericapp.ui.grupos;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GruposViewModel extends ViewModel
{
    private MutableLiveData<String> mText;

    public GruposViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is grupos fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}