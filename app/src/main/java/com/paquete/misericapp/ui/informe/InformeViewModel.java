package com.paquete.misericapp.ui.informe;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InformeViewModel extends ViewModel
{
    private MutableLiveData<String> mText;

    public InformeViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("ENVIAR INFORME DEL MES");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}