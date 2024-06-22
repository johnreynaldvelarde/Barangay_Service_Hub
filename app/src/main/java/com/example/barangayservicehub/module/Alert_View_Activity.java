package com.example.barangayservicehub.module;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;

public class Alert_View_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alert_view);

        String newsTitle = getIntent().getStringExtra("NEWS_TITLE");
        String newsDate = getIntent().getStringExtra("NEWS_DATE");
        String newsArticle = getIntent().getStringExtra("NEWS_CONTENT");

        TextView viewNewsTitle = findViewById(R.id.viewNewsTitle);
        TextView viewNewsDate = findViewById(R.id.viewNewsDate);
        TextView viewNewsArticle = findViewById(R.id.viewNewsArticle);

        viewNewsTitle.setText(newsTitle);
        viewNewsDate.setText(newsDate);
        viewNewsArticle.setText(newsArticle);


        ImageView btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}