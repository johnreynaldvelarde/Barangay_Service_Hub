package com.example.barangayservicehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
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
            //layoutPassword.setHelperText("Please enter the password");
            layoutPassword.setError("");
        } else {
            password.setError(null);
        }

        if(confirmPasswordText.isEmpty()){
            layoutPassword.setError("");
            //confirm_password.setError("This field cannot empty");
        } else {
            confirm_password.setError(null);
        }

        if(nameText.isEmpty() || passwordText.isEmpty()){
            // Alert the user with a Toast message
            Toast.makeText(this, "Please fill in both username and password fields", Toast.LENGTH_SHORT).show();
        }
        else if (emailText.isEmpty()) {
            Toast.makeText(this, "Please fill the email", Toast.LENGTH_SHORT).show();
        } else{
            MyConnection conn = new MyConnection(RegisterActivity.this);
            conn.addRegisterUser(nameText, emailText, passwordText, Boolean.FALSE);
            nextLaunchLogin();
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