package com.paquete.misericapp.ui.cartas;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CartasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("CARTAS PARA LA CONGREGACIÓN");
    }

    public LiveData<String> getText() {
        return mText;
    }
}