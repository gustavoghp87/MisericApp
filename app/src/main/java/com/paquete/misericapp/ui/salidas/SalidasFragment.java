package com.paquete.misericapp.ui.salidas;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.paquete.misericapp.R;

public class SalidasFragment extends Fragment {
    private SalidasViewModel salidasViewModel;
    private ImageView salidas1;
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        salidasViewModel = new ViewModelProvider(this).get(SalidasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_salidas, container, false);
        final TextView textView = root.findViewById(R.id.text_salidas);
        salidasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });

        salidas1 = root.findViewById(R.id.salidas1);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("Salidas/");
        showData();


        return root;

    }

    private void showData() {
        StorageReference fileReference = storageReference.child("febr2020.png");
        Task<Uri> uri = fileReference.getDownloadUrl();
//        fileReference.putFile();
    }
}