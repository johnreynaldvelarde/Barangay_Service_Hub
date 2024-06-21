package com.example.barangayservicehub.admin_module;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.example.barangayservicehub.core.function;

public class Admin_Program extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1001;

    private static final int GALLERY_REQUEST_CODE = 1002;

    ImageButton btnCameraGallery;
    ImageView btnBack, preview_Image;

    ProgressBar progressBar;

    TextView viewDate, title, description, comment, reportSubmitBtn;
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

        viewDate = findViewById(R.id.program_date_now);
        String currentDate = function.getCurrentDate();
        viewDate.setText(currentDate);

        btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}