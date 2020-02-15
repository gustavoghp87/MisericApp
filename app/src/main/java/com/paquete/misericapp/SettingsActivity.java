package com.paquete.misericapp;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    Button btnDark;
    Button btnLight;
    Button mButtonSignOut;
    Button btn_english;
    Button btn_espaniol;

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
        sharedPreferences = getSharedPreferences("LANG", MODE_PRIVATE);
        int lang = sharedPreferences.getInt("language", 1);
        switch (lang)
        {
            case 1: changeLanguage("es");
                break;
            case 2: changeLanguage("en");
        }
        setContentView(R.layout.activity_settings);
        showToolbar(getResources().getString(R.string.action_settings), true);


        btnDark = findViewById(R.id.btnDark);
        btnLight = findViewById(R.id.btnLight);

        btnDark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sharedPreferences.edit().putInt("THEME", 2).apply();
                Toast.makeText(getApplicationContext(), "Modo oscuro activado", Toast.LENGTH_LONG).show();
                reloadPage();
            }
        });

        btnLight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sharedPreferences.edit().putInt("THEME", 1).apply();
                Toast.makeText(getApplicationContext(), "Modo claro activado", Toast.LENGTH_LONG).show();
                reloadPage();
            }
        });


//        sharedPreferences = getSharedPreferences("IDIOMA", MODE_PRIVATE);
//        int lang = sharedPreferences.getInt("LANG", 1);
//        switch (lang)
//        {
//            case 1:
//                changeLanguage("en");
//                break;
//
//            case 2:
//                changeLanguage("es");
//                break;
//        }


        // LOG OUT BUTTON
        mAuth = FirebaseAuth.getInstance();
        mButtonSignOut = findViewById(R.id.btnSignOut);
        mButtonSignOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sharedPreferences.edit().putInt("THEME", 1).apply();
                mAuth.signOut();
                startActivity(new Intent(SettingsActivity.this, OpenActivity.class));
                finish();
            }
        });


        // LANGUAGES
        btn_english = findViewById(R.id.btnEnglish);
        btn_english.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sharedPreferences.edit().putInt("language", 2).apply();
                changeLanguage("en");
                Toast.makeText(getApplicationContext(), "English Language", Toast.LENGTH_LONG).show();
                reloadPage();
            }
        });

        btn_espaniol = findViewById(R.id.btnEspaniol);
        btn_espaniol.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sharedPreferences.edit().putInt("language", 1).apply();
                changeLanguage("es");
                Toast.makeText(getApplicationContext(), "Idioma Español", Toast.LENGTH_LONG).show();
                reloadPage();
            }
        });
    }


    public void changeLanguage(String language2)
    {
        Locale locale = new Locale(language2);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void reloadPage()
    {
        Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void showToolbar(String title, boolean upButton)
    {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}