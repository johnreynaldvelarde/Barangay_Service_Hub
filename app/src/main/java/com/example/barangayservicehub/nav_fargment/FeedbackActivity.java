package com.example.barangayservicehub.nav_fargment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
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
import com.example.barangayservicehub.services_module.RequestServicesActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FeedbackActivity extends AppCompatActivity {

    TextView btnTextFeedback, feedTitle, feedRating, feedComment;

    ProgressBar progressBar;

    FrameLayout btnFeedback;

    final Firebase_Connect connect;

    public FeedbackActivity(){

        connect = new Firebase_Connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feedback);


        btnTextFeedback = findViewById(R.id.textFeedSubmitBtn);
        btnFeedback = findViewById(R.id.btnSubmit);

        progressBar = findViewById(R.id.progressBar);
        progressBar.bringToFront();

        ImageView btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                btnTextFeedback.setVisibility(View.INVISIBLE);
                submitFeedback();
            }
        });
    }

    public void submitFeedback(){

        TextInputLayout layoutTitle = findViewById(R.id.layout_feed_title);
        TextInputLayout layoutRating = findViewById(R.id.layout_feed_rating);
        TextInputLayout layoutComment = findViewById(R.id.layout_feed_comment);


        feedTitle = findViewById(R.id.feed_title);
        feedRating = findViewById(R.id.feed_rating);
        feedComment = findViewById(R.id.feed_comment);

        String title = feedTitle.getText().toString();
        String rating = feedRating.getText().toString();
        String comment = feedComment.getText().toString();

        feedTitle.addTextChangedListener(new TextWatcher() {
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

        feedRating.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutRating.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        feedComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutComment.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        if(title.isEmpty()){

            finishActionProgressBar();
            layoutTitle.setError("* Fill in the blank");

        } else if (rating.isEmpty()) {

            finishActionProgressBar();
            layoutRating.setError("* Fill in the blank");

        } else if (comment.isEmpty()) {

            finishActionProgressBar();
            layoutComment.setError("* Fill in the blank");

        }else {

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String userID = sharedPreferences.getString("userID", "");

            boolean result = connect.addFeedbackReview(userID, title, rating, comment);

            if(result){

                finishActionProgressBar();

                Toast.makeText(FeedbackActivity.this, "Successfully submit a feedback", Toast.LENGTH_SHORT).show();
                finish();

            }else {
                Toast.makeText(FeedbackActivity.this, "Failed to submit feedback and review", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void finishActionProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
        btnTextFeedback.setVisibility(View.VISIBLE);
    }
}