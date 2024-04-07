package com.example.barangayservicehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextRegisterAccount();
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
    public void checkCredentials(){
        EditText name = findViewById(R.id.register_name);
        EditText email = findViewById(R.id.register_email);
        EditText password = findViewById(R.id.register_password);
        EditText confirm_password = findViewById(R.id.confrim_password);

        TextInputLayout layoutName = findViewById(R.id.Register_Name_Text);
        TextInputLayout layoutPassword = findViewById(R.id.Register_Password_Text);
        @SuppressLint("CutPasteId") TextInputEditText editName = findViewById(R.id.register_name);

        String nameText = name.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPasswordText = confirm_password.getText().toString();

        if(nameText.isEmpty()){
            name.setError("Please enter the name");

        } else {
            name.setError(null);
        }

        if(emailText.isEmpty()){
            email.setError("Please enter the email");
        } else {
            email.setError(null);
        }

        if(passwordText.isEmpty()){
            layoutPassword.setHelperText("Please enter the password");
            layoutPassword.setError("");
        } else {
            password.setError(null);
        }

        if(confirmPasswordText.isEmpty()){
            confirm_password.setError("This field cannot empty");
        } else {
            confirm_password.setError(null);
        }

        if(nameText.isEmpty() || passwordText.isEmpty()){
            // Alert the user with a Toast message
            //Toast.makeText(this, "Please fill in both username and password fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void nextLaunchLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
    public void nextRegisterAccount(){
        checkCredentials();
    }
}