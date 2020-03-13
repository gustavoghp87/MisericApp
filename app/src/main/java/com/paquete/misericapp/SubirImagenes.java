package com.paquete.misericapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SubirImagenes extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button btnElegirImagen;
    private Button btnSubirImagen;
    private TextView textViewVerPizarra;
    private EditText editTextNombreImagen;
    private ImageView mImageViewFotos;
    private ProgressBar progressBarSubirImagenes;

    private Uri mImagenUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;
    String downloadUrl;
    String userEmail;
    FirebaseAuth refAuth;
    SimpleDateFormat currentDate, currentTime;
    String fechayhora;
    Date date;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_imagenes);
        showToolbar(getResources().getString(R.string.subir_imagenes), true);

        btnElegirImagen = findViewById(R.id.btnElegirFoto);
        btnSubirImagen = findViewById(R.id.btnSubirFoto);
        textViewVerPizarra = findViewById(R.id.text_view_mostrar_pizarra);
        editTextNombreImagen = findViewById(R.id.edit_text_elegir_foto);
        mImageViewFotos = findViewById(R.id.image_view_subir_fotos);
        progressBarSubirImagenes = findViewById(R.id.progress_bar_subir_foto);

        mStorageRef = FirebaseStorage.getInstance().getReference("Fotos");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Fotos");

        btnElegirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        // captura de fecha y hora
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        currentTime = new SimpleDateFormat("HH:mm:ss a", Locale.getDefault());
        date = new Date();
        fechayhora = currentDate.format(date) +" " +currentTime.format(date);
//        Toast.makeText(SubirImagenes.this, fechayhora, Toast.LENGTH_LONG).show();

        btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(SubirImagenes.this, "Subida en proceso...", Toast.LENGTH_LONG).show();
                } else {
                    uploadFile();
                }
            }
        });

        textViewVerPizarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizarra();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // imagen escogida, en miniatura
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImagenUri = data.getData();
            Picasso.get().
                    load(mImagenUri)
//                    .fit()
                    .resize(2000, 2000)
                    .onlyScaleDown()
                    .into(mImageViewFotos);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private  void uploadFile() {
        if (mImagenUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImagenUri));
            refAuth = FirebaseAuth.getInstance();
            userEmail = refAuth.getCurrentUser().getEmail();

            mUploadTask = fileReference.putFile(mImagenUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBarSubirImagenes.setProgress(0);
                            openPizarra();
                        }
                    }, 500);
//                    Toast.makeText(SubirImagenes.this, "Subida exitosa", Toast.LENGTH_LONG).show();

                    // base de datos
                    Task<Uri> urlTask = mUploadTask.continueWithTask(new Continuation() {
                        @Override
                        public Object then(@NonNull Task task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            downloadUrl = fileReference.getDownloadUrl().toString();
                            return fileReference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            downloadUrl = task.getResult().toString();
                            String emailPost = userEmail;
                            String titulo = "   " + editTextNombreImagen.getText().toString().trim();
//                            String archivo = mStorageRef.getDownloadUrl().toString();
//                            String archivo = taskSnapshot.getDownloadUrl().toString();

                            UploadFoto uploadFoto = new UploadFoto(emailPost, fechayhora, titulo, downloadUrl);
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(uploadFoto);

//                            Toast.makeText(SubirImagenes.this, downloadUrl, Toast.LENGTH_LONG).show();
                        }
                    });



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SubirImagenes.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBarSubirImagenes.setProgress((int) progress);
                }
            });
        } else {
            Toast.makeText(this, "Seleccionar archivo primero", Toast.LENGTH_LONG).show();
        }
    }

    private void openPizarra() {
        Intent intent = new Intent(SubirImagenes.this, Pizarra.class);
        startActivity(intent);
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
