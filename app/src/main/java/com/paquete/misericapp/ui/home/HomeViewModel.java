package com.paquete.misericapp.ui.home;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel
{
    private MutableLiveData<String> mText;
    public HomeViewModel()
    {
        mText = new MutableLiveData<>();
<<<<<<< HEAD
        mText.setValue("TABLERO DE ANUNCIOS");
    }
    public LiveData<String> getText()
    {
=======
        mText.setValue("This is home fragment");
    }
    public LiveData<String> getText() {
>>>>>>> master/master
        return mText;
    }
}