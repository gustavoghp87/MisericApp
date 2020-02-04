package com.paquete.misericapp.ui.grupos;
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

public class GruposFragment extends Fragment {

    private GruposViewModel gruposViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gruposViewModel =
                ViewModelProviders.of(this).get(GruposViewModel.class);
        View root = inflater.inflate(R.layout.fragment_grupos, container, false);
        final TextView textView = root.findViewById(R.id.text_grupos);
        gruposViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}