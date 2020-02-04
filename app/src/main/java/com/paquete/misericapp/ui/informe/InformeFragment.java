package com.paquete.misericapp.ui.informe;
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
import androidx.lifecycle.ViewModelProvider;
import com.paquete.misericapp.R;

public class InformeFragment extends Fragment
{
    private InformeViewModel informeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        informeViewModel = new ViewModelProvider(this).get(InformeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_informe, container, false);
        final TextView textView = root.findViewById(R.id.text_informe);
        informeViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });
        return root;
    }
}