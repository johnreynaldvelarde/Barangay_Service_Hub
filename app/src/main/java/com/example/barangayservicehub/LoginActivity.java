package com.example.barangayservicehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
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

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;
    boolean passwordVisible;

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
    public void showPasword(){

    }

    public void checkCredentials(){
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();

        if(usernameText.isEmpty()){
            // Show error icon and message inside the username EditText
            username.setError("Please enter username");
        } else {
            // Clear any error icon and message if username is not empty
            username.setError(null);
        }

        if(passwordText.isEmpty()){
            // Show error icon and message inside the password EditText
            //password.setError("Please enter password");
        } else {
            // Clear any error icon and message if password is not empty
            //password.setError(null);
        }

        if(usernameText.isEmpty() || passwordText.isEmpty()){
            // Alert the user with a Toast message
            //Toast.makeText(this, "Please fill in both username and password fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    public void nextLaunchHome(){
       checkCredentials();
    }
    public void nextLaunchRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}