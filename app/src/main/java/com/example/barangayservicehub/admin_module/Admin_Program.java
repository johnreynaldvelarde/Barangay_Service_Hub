package com.example.barangayservicehub.admin_module;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.example.barangayservicehub.core.function;
import com.google.android.material.textfield.TextInputLayout;

public class Admin_Program extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1001;

    private static final int GALLERY_REQUEST_CODE = 1002;

    ImageButton btnCameraGallery;
    ImageView btnBack, preview_Image;

    ProgressBar progressBar;

    FrameLayout btnFrame;

    TextView programTitle, programDescription, btnTextSubmit;

    TextInputLayout layoutTitle, layoutDescription;

    public TextView viewDate, title, description, comment, reportSubmitBtn;
    final Firebase_Connect connect;
    final function function;
    public Admin_Program(){
        connect = new Firebase_Connect();
        function = new function();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_program);

        viewDate = findViewById(R.id.date_now);
        String currentDate = function.getCurrentDate();
        viewDate.setText(currentDate);

        btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFrame = findViewById(R.id.btnFrameSubmit);
        btnTextSubmit = findViewById(R.id.btnTextSubmit);
        progressBar = findViewById(R.id.progressBar);
        btnFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                btnTextSubmit.setVisibility(View.INVISIBLE);
                addProgram();
            }
        });


    }

    public void addProgram(){

        layoutTitle = findViewById(R.id.layout_program_title);
        layoutDescription = findViewById(R.id.layout_program_article);

        programTitle = findViewById(R.id.program_title);
        programDescription = findViewById(R.id.program_article);

        String title  = programTitle.getText().toString();
        String description = programDescription.getText().toString();

        programTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutTitle.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        programDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutDescription.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(title.isEmpty()){

            function.finishActionProgressBar(progressBar,  btnTextSubmit );
            layoutTitle.setError("* Fill in the blank");

        } else if (description.isEmpty()) {
            function.finishActionProgressBar(progressBar,  btnTextSubmit );
            layoutDescription.setError("* Fill in the blank");
        }
        else{

            boolean result = connect.addPrograms(title, description);

            function.finishActionProgressBar(progressBar,  btnTextSubmit );
            if (result){
                Toast.makeText(Admin_Program.this, "Barangay program added successfully", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(Admin_Program.this, "Barangay program added error", Toast.LENGTH_SHORT).show();
            }
        }

    }
}