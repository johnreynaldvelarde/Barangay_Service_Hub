package com.example.barangayservicehub;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barangayservicehub.connector.Firebase_Connect;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.registration.MESSAGE";

    EditText username, password;
    TextInputLayout loginlayoutUsername;
    TextInputLayout loginLayoutPassword;
    Button btnLogin;

    Firebase_Connect connect;

    public LoginActivity(){
        connect = new Firebase_Connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLaunchHome();
            }
        });

        TextView btnNewUser = findViewById(R.id.btn_NewUser);

        String completeText = getString(R.string.new_user_register_now);

        int startIndex = completeText.indexOf("Register Now");

        if(startIndex != -1){
            SpannableString spannableString = new SpannableString(completeText);
            spannableString.setSpan(
                    new ForegroundColorSpan(Color.BLUE),
                    startIndex,
                    startIndex + "Register Now".length(),
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            btnNewUser.setText(spannableString);
        }
        else{
            btnNewUser.setText(completeText);
        }

        // register new account
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLaunchRegister();
            }
        });
    }

    public void checkCredentials(){
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        loginLayoutPassword = findViewById(R.id.login_password_layout);
        loginlayoutUsername = findViewById(R.id.login_username_layout);

        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginlayoutUsername.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginLayoutPassword.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(usernameText.isEmpty()){
            loginlayoutUsername.setError("* Fill in the blank");
        } else if (passwordText.isEmpty())
        {
            loginLayoutPassword.setError("* Fill in the blank");
        } else {


            checkUserAccount();
        }
    }

    public void checkUserAccount(){
        String userUsername = username.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User_Account");
        Query checkUserDatabase = reference.orderByChild("name").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    // User exists
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);

                        if(passwordFromDB.equals(userPassword)){
                            // Password matches
                            loginlayoutUsername.setError(null);
                            Intent intent = null;

                            // Pass user data if needed
                            String nameFromDB = userSnapshot.child("name").getValue(String.class);
                            String emailFromDB = userSnapshot.child("email").getValue(String.class);
                            Integer accountTypeFromDB = userSnapshot.child("accountType").getValue(Integer.class);


                            if(accountTypeFromDB == 0){
                                intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra(EXTRA_MESSAGE, nameFromDB);
                            }
                            else{
                                intent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                            }

                            if(intent != null){
                                startActivity(intent);
                            }
                            return;
                        } else {
                            loginLayoutPassword.setError("Invalid Password");
                            password.requestFocus();
                        }
                    }
                } else {
                    // User does not exist
                    loginlayoutUsername.setError("User does not exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    public void nextLaunchHome(){
       checkCredentials();
    }
    public void nextLaunchRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}