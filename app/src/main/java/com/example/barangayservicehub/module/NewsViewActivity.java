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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsViewActivity extends AppCompatActivity {

    TextView viewNewsTitle, viewNewsArticle, viewNewsDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_view);

        String newsTitle = getIntent().getStringExtra("NEWS_TITLE_EXTRA");
        String newsDate = getIntent().getStringExtra("NEWS_DATE_EXTRA");
        String newsArticle = getIntent().getStringExtra("NEWS_ARTICLE_EXTRA");

        viewNewsTitle = findViewById(R.id.viewNewsTitle);
        viewNewsDate = findViewById(R.id.viewNewsDate);
        viewNewsArticle = findViewById(R.id.viewNewsArticle);

        viewNewsTitle.setText(newsTitle);
        viewNewsArticle.setText(newsArticle);

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
            Date date = inputFormat.parse(newsDate);
            String formattedDate = outputFormat.format(date);
            viewNewsDate.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ImageView btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}