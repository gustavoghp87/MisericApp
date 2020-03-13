package com.paquete.misericapp;
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

public class SettingsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    Button btnDark;
//    Button btnLight;
    Button mButtonSignOut;
    Button btn_english;
//    Button btn_espaniol;
    int theme;
    int lang;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);   //una única vez por actividad

        //theme
        theme = sharedPreferences.getInt("THEME", 1);
        switch (theme)
        {
            case 1: setTheme(R.style.AppTheme);
                break;
            case 2: setTheme(R.style.AppTheme2);
                break;
        }

        //language
        lang = sharedPreferences.getInt("language", 1);
        switch (lang)
        {
            case 1: changeLanguage("es");
                break;
            case 2: changeLanguage("en");
                break;
        }
        setContentView(R.layout.activity_settings);
        showToolbar(getResources().getString(R.string.action_settings), true);


        btnDark = findViewById(R.id.btnDark);
//        btnLight = findViewById(R.id.btnLight);
        if (theme == 1) {
            btnDark.setText(R.string.btnDarky);
        } else {
            btnDark.setText(R.string.btnLighty);
        }
        btnDark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (theme == 1) {
                    sharedPreferences.edit().putInt("THEME", 2).apply();
                    Toast.makeText(getApplicationContext(), "Modo oscuro activado", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences.edit().putInt("THEME", 1).apply();
                    Toast.makeText(getApplicationContext(), "Modo claro activado", Toast.LENGTH_SHORT).show();
                }
                reloadPage();
            }
        });

//        btnLight.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                sharedPreferences.edit().putInt("THEME", 1).apply();
//                Toast.makeText(getApplicationContext(), "Modo claro activado", Toast.LENGTH_SHORT).show();
//                reloadPage();
//            }
//        });

        // LANGUAGES
        btn_english = findViewById(R.id.btnEnglish);
        if (lang == 1) {
            btn_english.setText(R.string.btnEnglishy);
        } else {
            btn_english.setText(R.string.btnEspanioly);
        }
        btn_english.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (lang == 1) {
                    sharedPreferences.edit().putInt("language", 2).apply();
                    changeLanguage("en");
                    Toast.makeText(getApplicationContext(), "English Language", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences.edit().putInt("language", 1).apply();
                    changeLanguage("es");
                    Toast.makeText(getApplicationContext(), "Idioma Español", Toast.LENGTH_SHORT).show();
                }
                reloadPage();
            }
        });

//        btn_espaniol = findViewById(R.id.btnEspaniol);
//        btn_espaniol.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                reloadPage();
//            }
//        });

        // LOG OUT BUTTON
        mAuth = FirebaseAuth.getInstance();
        mButtonSignOut = findViewById(R.id.btnSignOut);
        mButtonSignOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sharedPreferences.edit().putInt("THEME", 1).apply();
                sharedPreferences.edit().putInt("LOGIN", 1).apply();
                sharedPreferences.edit().putInt("language", 1).apply();
                sharedPreferences.edit().putInt("VERIF", 1).apply();
                sharedPreferences.edit().putString("userName", "");
                mAuth.signOut();
                startActivity(new Intent(SettingsActivity.this, OpenActivity.class));
                finish();
            }
        });
    }

    public void reloadPage()
    {
        Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void changeLanguage(String language2) {
//        new LanguageImpl().changeLanguage(language2);
        Locale locale = new Locale(language2);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}