package com.paquete.misericapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ProfileReadOnly extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    FirebaseUser user;
    FirebaseAuth mAuth;
    String userUID;

    DatabaseReference databaseInformeLectura;
    Spinner spinnerLectura;
    Button btnLectura;
    String mesLectura;
    String nombre1;
    TextView mTextViewMes;

    ListView listViewInformesX;
    List<Informe> informes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //permiso
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userUID = user.getUid();
        if (!userUID.equals("Gs245UrpIucQ3zgtseiIp0bnxMz1") && !userUID.equals("1S8dUOm8gSYF2xHHqROMUFBvVi82")) {
            startActivity(new Intent(ProfileReadOnly.this, MainActivity.class));
        }

        //theme
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        int theme = sharedPreferences.getInt("THEME", 1);
        switch (theme) {
            case 1:
                setTheme(R.style.AppTheme);
                break;
            case 2:
                setTheme(R.style.AppTheme2);
                break;
        }

        setContentView(R.layout.activity_profile_read_only);
        showToolbar(getResources().getString(R.string.informesDB), true);

        //asignación de mes
        mesLectura = sharedPreferences.getString("MES_LECTURA", "Marzo 2020");
        mTextViewMes = findViewById(R.id.textViewMes);
        mTextViewMes.setText(mesLectura);

        btnLectura = findViewById(R.id.btnLectura);
        btnLectura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lecturaMes();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        informes = new ArrayList<>();
        listViewInformesX = findViewById(R.id.listViewInformes);
//        String name1 = sharedPreferences.getString("userName", "error al cargar nombre desde memoria de dispositivo");
//        String name1 = "Gustavo HP 16";
//        String mesLectura = "Marzo 2020";

        // carga para leer DB
        databaseInformeLectura = FirebaseDatabase.getInstance().getReference("Informes");
        databaseInformeLectura.child(mesLectura).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                informes.clear();
                mTextViewMes = findViewById(R.id.textViewMes);
                mTextViewMes.setText(mesLectura);

                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Informe informe = postSnapshot.getValue(Informe.class);
                        informes.add(informe);
                    }
                    InformesList informeAdapter = new InformesList(ProfileReadOnly.this, informes);
                    listViewInformesX.setAdapter(informeAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileReadOnly.this, "No encontré datos para este mes", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void lecturaMes() {
        spinnerLectura = findViewById(R.id.spinner_lectura);
        mesLectura = spinnerLectura.getSelectedItem().toString();
        sharedPreferences.edit().putString("MES_LECTURA", mesLectura).apply();
        startActivity(new Intent(ProfileReadOnly.this, ProfileReadOnly.class));
    }

    public void showToolbar(String title, boolean upButton)
    {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}