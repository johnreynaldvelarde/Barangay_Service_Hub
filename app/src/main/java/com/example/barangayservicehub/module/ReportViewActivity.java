package com.example.barangayservicehub.module;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.barangayservicehub.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportViewActivity extends AppCompatActivity {

    TextView viewReportTitle, viewReportDate, viewReportComment;
    ImageView viewReportImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report_view);

        viewReportTitle = findViewById(R.id.viewReportTitle);
        viewReportDate = findViewById(R.id.viewReportDate);
        viewReportComment = findViewById(R.id.viewReportComment);
        viewReportImage = findViewById(R.id.viewReportImage);

        String reportTitle = getIntent().getStringExtra("REPORT_TITLE");
        String reportDate = getIntent().getStringExtra("REPORT_DATE");
        String reportComment = getIntent().getStringExtra("REPORT_COMMENT");
        String imageURL = getIntent().getStringExtra("REPORT_IMAGE_URL");

        if (TextUtils.isEmpty(imageURL)) {
            Glide.with(this).load(R.drawable.blank_image).into(viewReportImage);
        } else {
            Glide.with(this).load(imageURL).into(viewReportImage);
        }

        viewReportTitle.setText(reportTitle);

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
            Date date = inputFormat.parse(reportDate);
            String formattedDate = outputFormat.format(date);
            viewReportDate.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewReportComment.setText(reportComment);

        ImageView btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}