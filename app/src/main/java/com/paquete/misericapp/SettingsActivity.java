package com.paquete.misericapp;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity
{
    private Button mButtonSignOut;
    private FirebaseAuth mAuth;
    //    private Switch switch_en;
//    private Switch switch_darky;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        showToolbar(getResources().getString(R.string.action_settings), true);


        // LOG OUT BUTTON
        mAuth = FirebaseAuth.getInstance();
        mButtonSignOut = findViewById(R.id.btnSignOut);

        mButtonSignOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(SettingsActivity.this, OpenActivity.class));
                finish();
            }
        });


        // LANGUAGES
        Switch switch_en = findViewById(R.id.switch_english);
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        int language = sharedPreferences.getInt("idioma", 1);

        switch_en.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked) {
                    sharedPreferences.edit().putInt("language", 2).apply();
                    Toast.makeText(getApplicationContext(), "Switch is on", Toast.LENGTH_LONG).show();

                    reloadPage();
                } else {
                    sharedPreferences.edit().putInt("language", 1).apply();
                    Toast.makeText(getApplicationContext(), "Switch is off", Toast.LENGTH_LONG).show();
                }
            }
        });

        switch (language)
        {
            case 1:
                changeLanguage("en");
                break;

            case 2:
                changeLanguage("es");
                break;
        }

        //tema oscuro
        Switch switch_dark = findViewById(R.id.switch_dark);
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        int THEME = sharedPreferences.getInt("THEME", 1);

        switch_dark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPreferences.edit().putInt("THEME", 2).apply();
                    Toast.makeText(getApplicationContext(), "Modo oscuro activado", Toast.LENGTH_LONG).show();

                    reloadPage();
                } else {
                    sharedPreferences.edit().putInt("THEME", 1).apply();
                    Toast.makeText(getApplicationContext(), "Modo oscuro desactivado", Toast.LENGTH_LONG).show();
                }
            }
        });

        switch (THEME)
        {
            case 1: setTheme(R.style.AppTheme);
                break;

            case 2: setTheme(R.style.AppTheme2);
                break;
        }
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
//        Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
//        startActivity(intent);
    }


//    public void loadLocale()
//    {
//        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
//        String language = prefs.getString("My lang", "");
//        changeLanguage(language);
//    }


    public void showToolbar(String title, boolean upButton)
    {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}