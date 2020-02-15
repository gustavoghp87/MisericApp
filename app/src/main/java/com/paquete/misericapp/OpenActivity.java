package com.paquete.misericapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;

public class OpenActivity extends AppCompatActivity
{
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
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
        setContentView(R.layout.activity_open);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(OpenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}