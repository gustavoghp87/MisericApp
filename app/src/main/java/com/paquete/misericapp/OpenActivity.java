package com.paquete.misericapp;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;

<<<<<<< HEAD
public class OpenActivity extends AppCompatActivity
{
    private final int SPLASH_DISPLAY_LENGTH = 1000;

=======
public class OpenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 6000;
>>>>>>> master/master
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

<<<<<<< HEAD
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
=======
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the MainActivity. */
>>>>>>> master/master
                Intent intent = new Intent(OpenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}