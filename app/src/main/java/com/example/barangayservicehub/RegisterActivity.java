package com.example.barangayservicehub;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barangayservicehub.all_class.Firebase_Connect;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email , password, confirm_password;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    Firebase_Connect connect;

    public RegisterActivity(){
        connect = new Firebase_Connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        //mAuth = FirebaseAuth.getInstance();


        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_account();

            }
        });

        TextView btnAlready = findViewById(R.id.btn_Already);

        String completeText = getString(R.string.already_have_account_sign_in);

        int startIndex = completeText.indexOf("Sign in");

        if(startIndex != -1){
            SpannableString spannableString = new SpannableString(completeText);
            spannableString.setSpan(
                    new ForegroundColorSpan(Color.BLUE),
                    startIndex,
                    startIndex + "Sign in".length(),
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            btnAlready.setText(spannableString);
        }
        else{
            btnAlready.setText(completeText);
        }

        btnAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLaunchLogin();
            }
        });
    }

    public void nextLaunchLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void register_account(){

        progressBar = findViewById(R.id.progressBar);

        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        confirm_password = findViewById(R.id.confrim_password);


        TextInputLayout layoutName = findViewById(R.id.Register_Name_Text);
        TextInputLayout layoutEmail = findViewById(R.id.Register_Email_Text);
        TextInputLayout layoutPassword = findViewById(R.id.Register_Password_Text);
        TextInputLayout layoutConfirm = findViewById(R.id.Register_Confrim_Text);

        String nameText = name.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPasswordText = confirm_password.getText().toString();


        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutName.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutEmail.setError(null);
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
                layoutPassword.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutConfirm.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(nameText.isEmpty()){
            layoutName.setError("* Fill in the blank");
        }
        else if (emailText.isEmpty()){
            layoutEmail.setError("* Fill in the blank");
        }
        else{

            if(passwordText.isEmpty()){
                layoutPassword.setError("* Required");
                return;
            } else if (passwordText.length() < 8) {
                layoutPassword.setError("Password must be 8 characters long.");
                return;
            } else if (!passwordText.matches(".*[@#$%^&+=].*")){
                layoutPassword.setError("Password must contain a special character.");
                return;
            } else {
                layoutPassword.setError(null);
            }

            if(!passwordText.equals(confirmPasswordText)){
                layoutConfirm.setError("Password do not match");
                if(confirmPasswordText.isEmpty()){
                    layoutConfirm.setError("* Required");
                }
                return;
            } else {
                layoutConfirm.setError(null);
            }

            progressBar.setVisibility(View.VISIBLE);

            connect.checkExistingUser(nameText, emailText, new Firebase_Connect.UserNameCheckCallback() {
                @Override
                public void onUserNameCheckResult(boolean usernameExists) {
                    progressBar.setVisibility(View.GONE);

                    if (usernameExists) {

                        layoutName.setError("* Username already exists.");
                    } else {

                        layoutName.setError(null);
                    }

                    // Check if email already exists
                    connect.checkExistingEmail(emailText, new Firebase_Connect.EmailCheckCallback() {
                        @Override
                        public void onEmailCheckResult(boolean emailExists) {
                            if (emailExists) {
                                // Display error message if email already exists
                                layoutEmail.setError("* Email already exists.");
                            } else {

                                layoutEmail.setError(null);
                            }

                            // Proceed with registration if both username and email are unique
                            if (!usernameExists && !emailExists) {
                                boolean result = connect.Register(nameText, emailText, passwordText, 0);
                                if (result) {
                                    nextLaunchLogin();
                                    Toast.makeText(RegisterActivity.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Registration failed
                                    Toast.makeText(RegisterActivity.this, "Registration failed. Please try again later.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onEmailCheckError(DatabaseError error) {

                            Toast.makeText(RegisterActivity.this, "Error checking email: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override
                public void onUserNameCheckError(DatabaseError error) {
                    // Handle error if occurred during username check
                    Toast.makeText(RegisterActivity.this, "Error checking username: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE); // Always hide progress bar after checking
                }
            });
        }
    }
}