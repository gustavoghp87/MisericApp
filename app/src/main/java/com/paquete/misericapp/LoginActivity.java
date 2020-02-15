package com.paquete.misericapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity
{
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;

    private String email;
    private String password;

    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;

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
        setContentView(R.layout.activity_login);

        mEditTextEmail      = findViewById(R.id.username);
        mEditTextPassword   = findViewById(R.id.password);
        Button mButtonLogin = findViewById(R.id.login);

        mAuth = FirebaseAuth.getInstance();

        mButtonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email    = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty())
                {
                    loginUser();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Complete los datos primero", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser()
    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesión \n Revise los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goCreateAccount(View view)
    {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goEnter (View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goEnterProv(View view)
    {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
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