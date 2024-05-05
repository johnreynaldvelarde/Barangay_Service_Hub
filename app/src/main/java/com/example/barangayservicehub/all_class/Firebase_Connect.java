package com.example.barangayservicehub.all_class;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.barangayservicehub.all_class.User_Account;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

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
