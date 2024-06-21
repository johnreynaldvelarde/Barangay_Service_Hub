package com.example.barangayservicehub.core;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class function {

    ProgressBar progressBar;
    TextView textView;

    public String getCurrentDate() {
        // Create a SimpleDateFormat object with desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Get current date
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String formatDate() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(dateNow);
    }

    public void finishActionProgressBar(ProgressBar progressBar, TextView textView){
        progressBar.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
    }


}
