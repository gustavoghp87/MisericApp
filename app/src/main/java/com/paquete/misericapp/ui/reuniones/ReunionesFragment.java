package com.paquete.misericapp.ui.reuniones;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.paquete.misericapp.R;

public class ReunionesFragment extends Fragment
{
    private ReunionesViewModel reunionesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        reunionesViewModel = new ViewModelProvider(this).get(ReunionesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reuniones, container, false);
        final TextView textView = root.findViewById(R.id.text_reuniones);
        reunionesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}