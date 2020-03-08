package com.paquete.misericapp.ui.salidas;
import android.media.session.PlaybackState;
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

public class SalidasFragment extends Fragment
{
    private SalidasViewModel salidasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        salidasViewModel = new ViewModelProvider(this).get(SalidasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_salidas, container, false);
        final TextView textView = root.findViewById(R.id.text_salidas);
        salidasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
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