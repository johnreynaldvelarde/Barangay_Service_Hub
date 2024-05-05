package com.example.barangayservicehub.all_class;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.barangayservicehub.all_class.User_Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CountDownLatch;

public class Firebase_Connect {

    private final DatabaseReference mDatabase;
    private boolean exists;

    public Firebase_Connect(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    // Register Activity
    public boolean Register(String name, String email, String password, int account_type ){
        try {

            String User_ID = mDatabase.child("User_Account").push().getKey();
            User_Account register = new User_Account(name, email, password, account_type);
            mDatabase.child("User_Account").child(User_ID).setValue(register);

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public boolean checkExistingUserName(String username) {
        final CountDownLatch latch = new CountDownLatch(1);
        DatabaseReference userAccountRef = mDatabase.child("User_Account");

        userAccountRef.orderByChild("name").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exists = dataSnapshot.exists(); // Update the exists field
                latch.countDown();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                latch.countDown();
            }
        });

        try {
            latch.await(); // Wait for the database operation to complete
            return exists; // Return true if username exists, false otherwise
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
