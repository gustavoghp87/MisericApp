package com.paquete.misericapp.ui.limpieza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.paquete.misericapp.R;

public class LimpiezaFragment extends Fragment {

    private LimpiezaViewModel limpiezaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        limpiezaViewModel =
                ViewModelProviders.of(this).get(LimpiezaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_limpieza, container, false);
        final TextView textView = root.findViewById(R.id.text_limpieza);
        limpiezaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}