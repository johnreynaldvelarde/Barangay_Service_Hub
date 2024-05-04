package com.example.barangayservicehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.bottom_fragment.DashboardFragment;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.registration.MESSAGE";

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

    public void checkCredentials(){
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        TextInputLayout loginLayoutPassword = findViewById(R.id.login_password_layout);
        TextInputLayout loginlayoutUsername = findViewById(R.id.login_username_layout);

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

            try{
                MyConnection conn = new MyConnection(LoginActivity.this);

                Cursor cursor = conn.loginUserAccount(usernameText, passwordText);

                if(cursor !=null && cursor.moveToFirst()){

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, usernameText);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password!!!", Toast.LENGTH_SHORT).show();
                }
                if (cursor != null) {
                    cursor.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show();
            }
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