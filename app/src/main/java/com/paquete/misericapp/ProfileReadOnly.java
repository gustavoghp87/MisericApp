package com.paquete.misericapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Snapshot;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ProfileReadOnly extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    FirebaseUser user;
    FirebaseAuth mAuth;
    String userUID;
//    ArrayAdapter<String> adapter;
//    String[] default_items = new String[]{"Name", "Email", "uid"};
//    DatabaseReference databaseReference;
//    List<String> itemlist;
//    String uid;
//
//    FirebaseDatabase mFirebaseDatabase;
//    FirebaseAuth mAuth;
//    FirebaseAuth.AuthStateListener mAuthListener;
//    ListView mListView;
//    private static final String TAG = "ViewDatabase";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userUID = user.getUid();

        if (!userUID.equals("Gs245UrpIucQ3zgtseiIp0bnxMz1") && !userUID.equals("1S8dUOm8gSYF2xHHqROMUFBvVi82"))
        {
            startActivity(new Intent(ProfileReadOnly.this, MainActivity.class));
        }

        //theme
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        int theme = sharedPreferences.getInt("THEME", 1);
        switch (theme) {
            case 1:
                setTheme(R.style.AppTheme);
                break;
            case 2:
                setTheme(R.style.AppTheme2);
                break;
        }
        setContentView(R.layout.activity_profile_read_only);
        showToolbar(getResources().getString(R.string.informesDB), true);

//        mListView = findViewById(R.id.listView);
//        mAuth = FirebaseAuth.getInstance();
//        databaseReference = mFirebaseDatabase.getReference();
//        FirebaseUser user = mAuth.getCurrentUser();
//        uid = user.getUid();
//
//        mAuthListener = new FirebaseAuth.AuthStateListener()
//        {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
//            {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null)
//                {
//                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
//                    toastMessage("Successfully signed in with: " +user.getEmail());
//                } else {
//                    Log.d(TAG, "onAuthStateChanged:signed_out: " + user.getUid());
//                    toastMessage("Successfully signed out");
//                }
//            }
//        };
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ShowData(dataSnapshot);
//
//                itemlist.clear();
//                String user_name = dataSnapshot.child(uid).child("name").getValue(String.class);
//                String user_email = dataSnapshot.child(uid).child("email").getValue(String.class);
//                String user_uid = dataSnapshot.child(uid).child("uid").getValue(String.class);
//
//                itemlist.add(user_name);
//                itemlist.add(user_email);
//                itemlist.add(user_uid);
//
//                adapter = new ArrayAdapter<>(ProfileReadOnly.this, android.R.layout.simple_list_item_1, itemlist);
//                l1.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        })
    }

//    private void ShowData(DataSnapshot dataSnapshot) {
//        for(DataSnapshot ds : dataSnapshot.getChildren()) {
//            Informe informeSend = new Informe();
//            informeSend.setName(ds.child(uid).getValue(Informe.class).getName());
//            informeSend.setEmail(ds.child(uid).getValue(Informe.class).getName());
//            informeSend.setUid(ds.child(uid).getValue(Informe.class).getName());
//        }
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//        if (mAuthListener != null)
//        {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    public void showToolbar(String title, boolean upButton)
    {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
