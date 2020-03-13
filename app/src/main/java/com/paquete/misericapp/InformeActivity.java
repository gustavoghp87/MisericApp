package com.paquete.misericapp;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Locale;

public class InformeActivity extends AppCompatActivity
{
    private EditText mEditTextHoras;
    private EditText mEditTextPublic;
    private EditText mEditTextVideos;
    private EditText mEditTextRevisitas;
    private EditText mEditTextEstudios;

    String horas;
    String publicaciones;
    String videos;
    String revisitas;
    String estudios;

    SharedPreferences sharedPreferences;
    Spinner mesSpinner;
    DatabaseReference databaseInforme;
    DatabaseReference databaseInformeUser;
    String uid;
    String email1;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);

        //tema
        int theme = sharedPreferences.getInt("THEME", 1);
        switch (theme)
        {
            case 1: setTheme(R.style.AppTheme);
                break;
            case 2: setTheme(R.style.AppTheme2);
                break;
        }

        //idioma
        int lang = sharedPreferences.getInt("language", 1);
        switch (lang)
        {
            case 1: changeLanguage("es");
                break;
            case 2: changeLanguage("en");
        }

        setContentView(R.layout.activity_informe);
        showToolbar("ENVIAR INFORME DEL MES", true);

        // inicio tareas
        mEditTextHoras = findViewById(R.id.send_horas);
        mEditTextPublic = findViewById(R.id.send_public);
        mEditTextVideos = findViewById(R.id.send_videos);
        mEditTextRevisitas = findViewById(R.id.send_revisitas);
        mEditTextEstudios = findViewById(R.id.send_estudios);
        mesSpinner = findViewById(R.id.spinner_mes);

        Button mButtonSend = findViewById(R.id.button_sendreport);
        mButtonSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                botonEnviarInforme();
            }
        });
    }

    public void botonEnviarInforme() {
        horas = mEditTextHoras.getText().toString();
        if (horas.isEmpty()) {horas = "0";}

        publicaciones = mEditTextPublic.getText().toString();
        if (publicaciones.isEmpty()) {publicaciones = "0";}

        videos = mEditTextVideos.getText().toString();
        if (videos.isEmpty()) {videos = "0";}

        revisitas = mEditTextRevisitas.getText().toString();
        if (revisitas.isEmpty()) {revisitas = "0";}

        estudios = mEditTextEstudios.getText().toString();
        if (estudios.isEmpty()) {estudios = "0";}

        if (horas.indexOf('.') == -1)

        informeOk();
    }

    public void informeOk() {
        final String mesInforme = mesSpinner.getSelectedItem().toString();
        databaseInforme = FirebaseDatabase.getInstance().getReference("Informes");
        databaseInformeUser = FirebaseDatabase.getInstance().getReference("Users");

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
            email1 = user.getEmail();
        }
//        name1 = sharedPreferences.getString("userName", "error al cargar nombre desde memoria de dispositivo");

        AlertDialog.Builder builder = new AlertDialog.Builder(InformeActivity.this);
        builder.setTitle("Informe " +mesInforme);
        builder.setMessage("Confirma tu informe por favor:\n\n Correo: " +email1 +"\n Mes: " +mesInforme + "\n Horas: " + horas + "\n Publicaciones: " + publicaciones + "\n Videos: " + videos + "\n Revisitas: " + revisitas + "\n Estudios: " + estudios)
            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    String userEmail = user.getEmail();
//                    String uid = databaseInforme.push().getKey();
//                    String id = databaseInformeLectura.push().getKey();
                    Informe informe = new Informe(email1, horas, publicaciones, videos, revisitas, estudios);
                    Informe informeUser = new Informe(horas, publicaciones, videos, revisitas, estudios);
                    databaseInforme.child(mesInforme).child(uid).setValue(informe);
                    databaseInformeUser.child(uid).child(mesInforme).setValue(informeUser);

                    Toast.makeText(InformeActivity.this, "ENVIADO Horas: " + horas + ", publicaciones: " + publicaciones + ", videos: " + videos + ", revisitas: " + revisitas + ", estudios: " + estudios, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InformeActivity.this, MainActivity.class));
                }
            })
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(InformeActivity.this, "CANCELADO", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            })
            .show();
    }

    public void showToolbar(String title, boolean upButton)
    {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void changeLanguage(String language2)
    {
        Locale locale = new Locale(language2);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}