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

    float horas_send_float;
    int horas_send_int;
    int publicaciones_send;
    int videos_send;
    int revisitas_send;
    int estudios_send;

    SharedPreferences sharedPreferences;
    Spinner mesSpinner;
    DatabaseReference databaseInforme;
    DatabaseReference databaseInformeUser;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);

        int theme = sharedPreferences.getInt("THEME", 1);
        switch (theme)
        {
            case 1: setTheme(R.style.AppTheme);
                break;
            case 2: setTheme(R.style.AppTheme2);
                break;
        }

        int lang = sharedPreferences.getInt("language", 1);
        switch (lang)
        {
            case 1: changeLanguage("es");
                break;
            case 2: changeLanguage("en");
        }
        setContentView(R.layout.activity_informe);
        showToolbar("ENVIAR INFORME DEL MES", true);

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
                horas = mEditTextHoras.getText().toString();
                if (horas.isEmpty()) {horas = "0";}

                if (horas.indexOf('.') == -1)
                {
                    horas_send_int = Integer.parseInt(horas);
                }
                else
                {
                    horas_send_float = Float.parseFloat(horas);
                }

                publicaciones = mEditTextPublic.getText().toString();
                if (publicaciones.isEmpty()) {publicaciones = "0";}
                publicaciones_send = Integer.parseInt(publicaciones);

                videos = mEditTextVideos.getText().toString();
                if (videos.isEmpty()) {videos = "0";}
                videos_send = Integer.parseInt(videos);

                revisitas = mEditTextRevisitas.getText().toString();
                if (revisitas.isEmpty()) {revisitas = "0";}
                revisitas_send = Integer.parseInt(revisitas);

                estudios = mEditTextEstudios.getText().toString();
                if (estudios.isEmpty()) {estudios = "0";}
                estudios_send = Integer.parseInt(estudios);

                if (horas.indexOf('.') == -1)
                {
                    informeOkInt();
                }
                else
                {
                    informeOkFloat();
                }
            }
        });
    }

    public void informeOkInt() {
        final String mesInforme = mesSpinner.getSelectedItem().toString();
        final String name1 = sharedPreferences.getString("userName", "error al cargar nombre desde memoria de dispositivo");
        databaseInforme = FirebaseDatabase.getInstance().getReference("Informes").child(mesInforme);
        databaseInformeUser = FirebaseDatabase.getInstance().getReference("Users").child(name1);

        AlertDialog.Builder builder = new AlertDialog.Builder(InformeActivity.this, R.style.dialogo);
        builder.setTitle("Informe " +mesInforme);
        builder.setMessage("Confirma tu informe por favor:\n\n Mes: " +mesInforme + "\n Horas: " + horas_send_int + "\n Publicaciones: " + publicaciones_send + "\n Videos: " + videos_send + "\n Revisitas: " + revisitas_send + "\n Estudios: " + estudios_send)
            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String userEmail = user.getEmail();
                    String uid = databaseInforme.push().getKey();

                    InformeInt informeInt = new InformeInt(horas_send_int, publicaciones_send, videos_send, revisitas_send, estudios_send);
                    databaseInforme.child(name1).setValue(informeInt);
                    databaseInformeUser.child("Informes").child(mesInforme).setValue(informeInt);

                    Toast.makeText(InformeActivity.this, "ENVIADO Horas: " + horas_send_int + ", publicaciones: " + publicaciones_send + ", videos: " + videos_send + ", revisitas: " + revisitas_send + ", estudios: " + estudios_send, Toast.LENGTH_SHORT).show();
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

    public void informeOkFloat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(InformeActivity.this);
        builder.setTitle("INFORME MARZO");
        builder.setMessage("Confirma tu informe por favor:\n\n Horas: " + horas_send_float + "\n Publicaciones: " + publicaciones_send + "\n Videos: " + videos_send + "\n Revisitas: " + revisitas_send + "\n Estudios: " + estudios_send)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(InformeActivity.this, "ENVIADO Horas: " + horas_send_float + ", publicaciones: " + publicaciones_send + ", videos: " + videos_send + ", revisitas: " + revisitas_send + ", estudios: " + estudios_send, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(InformeActivity.this, "CANCELADO", Toast.LENGTH_SHORT).show();
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