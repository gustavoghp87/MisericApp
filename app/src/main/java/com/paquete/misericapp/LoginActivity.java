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
import com.google.firebase.auth.FirebaseUser;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;

    private String email;
    private String password;

    SharedPreferences sharedPreferences;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);

        //login
        int loging = sharedPreferences.getInt("LOGIN", 1);
        if (loging == 2) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                boolean emailVerified = user.isEmailVerified();
                if (emailVerified) {
                    sharedPreferences.edit().putInt("LOGIN", 2).apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        }
        // Si valor 1 y verif 1: nada, a crear cuenta
        // Si valor 1 y verif 2: a loguear
        // Si valor 2 y verif 1: esperar o crear otra cuenta
        // Si valor 2 y verif 2: entrar a main

        //theme
        int theme = sharedPreferences.getInt("THEME", 1);
        switch (theme) {
            case 1:
                setTheme(R.style.AppTheme);
                break;
            case 2:
                setTheme(R.style.AppTheme2);
                break;
        }

        //language
        int lang = sharedPreferences.getInt("language", 1);
        switch (lang) {
            case 1:
                changeLanguage("es");
                break;
            case 2:
                changeLanguage("en");
        }
        setContentView(R.layout.activity_login);

        mEditTextEmail = findViewById(R.id.username);
        mEditTextPassword = findViewById(R.id.password);
        Button mButtonLogin = findViewById(R.id.login);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();
                if (!email.isEmpty() && !password.isEmpty())
                {
                    loginUser();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Complete los datos primero", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    user = firebaseAuth.getCurrentUser();
                    boolean emailVerified = user.isEmailVerified();
                    if (emailVerified) {
                        sharedPreferences.edit().putInt("LOGIN", 2).apply();
                        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "El email no ha sido verificado\nRevise la casilla de correo no deseado", Toast.LENGTH_SHORT).show();
                        }
                } else {
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesión \n Revise los datos o cree una cuenta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goEnter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    public void goEnterProv(View view)
//    {
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//    }

    private void enterApp() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void changeLanguage(String language2) {
        Locale locale = new Locale(language2);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}