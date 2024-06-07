package com.example.barangayservicehub.module;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barangayservicehub.R;

public class ProgramsViewActivity extends AppCompatActivity {

    TextView viewProgramsTitle, viewProgramsDate, viewProgramsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_programs_view);

        viewProgramsTitle = findViewById(R.id.viewProgramTitle);
        viewProgramsDescription = findViewById(R.id.viewProgramArticle);

        String programTitle = getIntent().getStringExtra("PROGRAMS_TITLE");
        String programDescription = getIntent().getStringExtra("PROGRAMS_DESCRIPTION");

        viewProgramsTitle.setText(programTitle);
        viewProgramsDescription.setText(programDescription);

        ImageView btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}