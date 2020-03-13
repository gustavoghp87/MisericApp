package com.paquete.misericapp.ui.salidas;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalidasViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public SalidasViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("SALIDAS PARA LA PREDICACIÓN\nPLAZA MISERICORDIA");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}