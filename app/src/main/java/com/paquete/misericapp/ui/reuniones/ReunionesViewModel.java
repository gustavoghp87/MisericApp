package com.paquete.misericapp.ui.reuniones;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReunionesViewModel extends ViewModel
{
    private MutableLiveData<String> mText;

    public ReunionesViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("REUNIONES DE LA CONGREGACIÓN");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}