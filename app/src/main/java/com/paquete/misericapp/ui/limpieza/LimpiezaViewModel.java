package com.paquete.misericapp.ui.limpieza;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LimpiezaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LimpiezaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ASIGNACIONES DE LIMPIEZA DEL SALÓN");
    }

    public LiveData<String> getText() {
        return mText;
    }
}