package com.paquete.misericapp.ui.sonido;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SonidoViewModel extends ViewModel
{
    private MutableLiveData<String> mText;

    public SonidoViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("ASIGNACIONES DEL DEPARTAMENTO DE SONIDO");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}