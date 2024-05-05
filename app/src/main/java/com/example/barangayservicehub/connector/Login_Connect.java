package com.example.barangayservicehub.connector;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Connect {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Context mContext;

    public Login_Connect(){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public boolean Login(String username, String password){
        try {
            mDatabase.child("Users_Account").orderByChild("name").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String storedPassword = userSnapshot.child("password").getValue(String.class);

                        if (storedPassword != null && storedPassword.equals(password)) {
                            // Login successful
                            return;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

}
