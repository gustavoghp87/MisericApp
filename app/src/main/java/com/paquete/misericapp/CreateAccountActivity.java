package com.paquete.misericapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class CreateAccountActivity extends AppCompatActivity
{
    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextConfPassword;

    private String name1;
    private String email1;
    private String password1;
    private String confirm_password1;

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference mDatabase;
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

        int lang = sharedPreferences.getInt("language", 1);
        switch (lang)
        {
            case 1: changeLanguage("es");
                break;
            case 2: changeLanguage("en");
        }
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_title_createaccount), true);

        mEditTextName          = findViewById(R.id.name);
        mEditTextEmail         = findViewById(R.id.email);
        mEditTextPassword      = findViewById(R.id.password_createaccount);
        mEditTextConfPassword  = findViewById(R.id.confirmPassword);
        Button mButtonRegister = findViewById(R.id.joinUs);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mButtonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name1              = mEditTextName.getText().toString();
                email1             = mEditTextEmail.getText().toString();
                password1          = mEditTextPassword.getText().toString();
                confirm_password1  = mEditTextConfPassword.getText().toString();

                if (!name1.isEmpty() && !email1.isEmpty() && !password1.isEmpty())
                {
                    if (password1.equals(confirm_password1))
                    {
                        if (password1.length() >= 6)
                        {
                            createAccount();
                        }
                        else
                        {
                            Toast.makeText(CreateAccountActivity.this, "Mínimo 6 caracteres para la contraseña", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(CreateAccountActivity.this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(CreateAccountActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void createAccount()
    {
        mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task1)
            {
                if(task1.isSuccessful())
                {
                    agregarEnDatabase();
                }
                else
                {
                    Toast.makeText(CreateAccountActivity.this, "Algo [1] ha fallado \n ¿El email es correcto?", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void agregarEnDatabase()
    {
        String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        Map <String, Object> map = new HashMap<>();
        map.put("name", name1);
        map.put("email", email1);
        map.put("uid", id);
        mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task2)
            {
                if (task2.isSuccessful())
                {
                    conclusionCreate();
                }
                else
                {
                    Toast.makeText(CreateAccountActivity.this, "Algo [2] ha fallado \n No se pudo crear el usuario correctamente \n Consulte al administrador", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void conclusionCreate() {
//        sharedPreferences.edit().putString("userName", email1).apply();
        sharedPreferences.edit().putInt("LOGIN", 2).apply();
        user = mAuth.getCurrentUser();
        user.sendEmailVerification();
        Toast.makeText(CreateAccountActivity.this, "RECIBIRÁ UN EMAIL PARA CONFIRMAR ESTA CUENTA\n(REVISAR SPAM)", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(CreateAccountActivity.this, OpenActivity.class));
        finish();
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void changeLanguage(String language2) {
        Locale locale = new Locale(language2);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}