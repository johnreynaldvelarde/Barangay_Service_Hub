package com.example.barangayservicehub.module;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;

public class Notification_View_Activity extends AppCompatActivity {

    final Firebase_Connect connect;

    public  Notification_View_Activity(){
        connect = new Firebase_Connect();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification_view);

        String newsTitle = getIntent().getStringExtra("NEWS_TITLE_EXTRA");
        String newsDate = getIntent().getStringExtra("NEWS_DATE_EXTRA");
        String newsArticle = getIntent().getStringExtra("NEWS_ARTICLE_EXTRA");





    }
}