package com.example.barangayservicehub;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email , password, confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
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

    public void checkCredentials(){

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

            try {
                MyConnection conn = new MyConnection(RegisterActivity.this);
                conn.addRegisterUser(nameText, emailText, passwordText, Boolean.FALSE);
                nextLaunchLogin();
            } catch (Exception e) {
                // Handle any potential exceptions that might occur during registration
                e.printStackTrace();
                // You can show an error message to the user, for example:
                Toast.makeText(this, "Registration failed. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void nextRegisterAccount(){

        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        confirm_password = findViewById(R.id.confrim_password);

        String registerUsername = name.getText().toString();
        String registerEmail = email.getText().toString();
        String registerPassword = password.getText().toString();
        String registerConfirm = confirm_password.getText().toString();


        if(TextUtils.isEmpty(registerUsername)){
            Toast.makeText(getApplicationContext(), "The username is empty!!!" , Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(registerEmail)){
            Toast.makeText(getApplicationContext(), "The email is empty!!!" , Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(registerPassword)){
            Toast.makeText(getApplicationContext(), "The password is empty!!!" , Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(registerConfirm)){
            Toast.makeText(getApplicationContext(), "The confirm password is empty!!!" , Toast.LENGTH_SHORT).show();
        }
        else{
            MyConnection conn = new MyConnection(RegisterActivity.this);
            conn.addRegisterUser(registerUsername, registerEmail, registerPassword, Boolean.FALSE);
            nextLaunchLogin();
        }
    }
}