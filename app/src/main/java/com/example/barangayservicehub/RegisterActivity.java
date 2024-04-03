package com.example.barangayservicehub;

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
        EditText password = findViewById(R.id.register_password);

        String nameText = name.getText().toString();
        String passwordText = password.getText().toString();

        if(nameText.isEmpty()){
            // Show error icon and message inside the username EditText
            name.setError("Please enter fullname");
        } else {
            // Clear any error icon and message if username is not empty
            name.setError(null);
        }

        if(passwordText.isEmpty()){
            // Show error icon and message inside the password EditText
            password.setError("Please enter password");
        } else {
            // Clear any error icon and message if password is not empty
            password.setError(null);
        }

        if(nameText.isEmpty() || passwordText.isEmpty()){
            // Alert the user with a Toast message
            //Toast.makeText(this, "Please fill in both username and password fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, DashboardActivity.class);
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