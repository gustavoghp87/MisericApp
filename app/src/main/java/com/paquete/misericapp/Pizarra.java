package com.paquete.misericapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Pizarra extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<UploadFoto> mUploads;
    private ProgressBar mProgressCircle;
    private FloatingActionButton fab;

    LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizarra);
        showToolbar(getResources().getString(R.string.pizarra), true);

        mRecyclerView = findViewById(R.id.recyclerViewPizarra);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(Pizarra.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);            // estos dos invierten el orden de la pizarra
        mRecyclerView.setLayoutManager(mLayoutManager);

        fab = findViewById(R.id.fab_subir_foto);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            startActivity(new Intent(Pizarra.this, SubirImagenes.class));
            }
        });

        mUploads = new ArrayList<>();
        mProgressCircle = findViewById(R.id.progress_circle);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Fotos");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadFoto uploadFoto = postSnapshot.getValue(UploadFoto.class);
                    mUploads.add(uploadFoto);
                }
                mAdapter = new ImageAdapter(Pizarra.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Pizarra.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}

