package com.paquete.misericapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        showToolbar("ENVIAR INFORME DEL MES", true);

        mEditTextHoras = findViewById(R.id.send_horas);
        mEditTextPublic = findViewById(R.id.send_public);
        mEditTextVideos = findViewById(R.id.send_videos);
        mEditTextRevisitas = findViewById(R.id.send_revisitas);
        mEditTextEstudios = findViewById(R.id.send_estudios);
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
                        Toast.makeText(InformeActivity.this, "Horas: " + horas_send_int + ", publicaciones: " + publicaciones_send + ", videos: " + videos_send + ", revisitas: " + revisitas_send + ", estudios: " + estudios_send, Toast.LENGTH_SHORT).show();
                    }
                else
                    {
                        Toast.makeText(InformeActivity.this, "Horas: " + horas_send_float + ", publicaciones: " + publicaciones_send + ", videos: " + videos_send + ", revisitas: " + revisitas_send + ", estudios: " + estudios_send, Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    public void showToolbar(String title, boolean upButton)
    {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}