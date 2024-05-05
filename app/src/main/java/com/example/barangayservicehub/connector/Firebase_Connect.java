package com.example.barangayservicehub.connector;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Firebase_Connect {

    private final DatabaseReference mDatabase;
    private boolean exists;

    public Firebase_Connect(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    // create a admin automatically

    public void checkAndCreateAdminIfNeeded(){
        DatabaseReference userAccountRef = mDatabase.child("User_Account");
        userAccountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    createAdminUser();
                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void createAdminUser() {
        final String adminUsername = "admin";
        final String adminPassword = "admin12345";

        try {
            String userId = mDatabase.child("User_Account").push().getKey();
            User_Account adminUser = new User_Account(adminUsername, "", adminPassword, 1);
            mDatabase.child("User_Account").child(userId).setValue(adminUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Register Activity
    public boolean Register(String name, String email, String password, int account_type ){
        try {

            String userId = mDatabase.child("User_Account").push().getKey();
            User_Account register = new User_Account(name, email, password, account_type);
            mDatabase.child("User_Account").child(userId).setValue(register);

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    // existing username
    public void checkExistingUser(String username, String email, UserNameCheckCallback callback) {
        DatabaseReference userAccountRef = mDatabase.child("User_Account");
        Query query = userAccountRef.orderByChild("name").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean exists = dataSnapshot.exists();
                callback.onUserNameCheckResult(exists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onUserNameCheckError(error);
            }
        });
    }

    // existing email
    public void checkExistingEmail(String email, EmailCheckCallback callback) {
        DatabaseReference userAccountRef = mDatabase.child("User_Account");
        Query query = userAccountRef.orderByChild("email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean exists = dataSnapshot.exists();
                callback.onEmailCheckResult(exists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onEmailCheckError(error);
            }
        });
    }

    public interface UserNameCheckCallback {
        void onUserNameCheckResult(boolean exists);
        void onUserNameCheckError(DatabaseError error);
    }

    public interface EmailCheckCallback {
        void onEmailCheckResult(boolean exists);
        void onEmailCheckError(DatabaseError error);
    }
}
