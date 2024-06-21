package com.example.barangayservicehub.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class function {

    public String getCurrentDate() {
        // Create a SimpleDateFormat object with desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Get current date
        String currentDate = sdf.format(new Date());

        return currentDate;
    }
}
