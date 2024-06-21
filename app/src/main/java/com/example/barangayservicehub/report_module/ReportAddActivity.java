package com.example.barangayservicehub.report_module;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.example.barangayservicehub.core.function;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ReportAddActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1001;

    private static final int GALLERY_REQUEST_CODE = 1002;

    final Firebase_Connect connect;
    final function function;

    FrameLayout btnCrimeReport;
    ImageButton btnCameraGallery;
    ImageView btnBack, preview_Image;
    TextView viewDate, title, location, comment, reportSubmitBtn;


    ProgressBar progressBar;

    public ReportAddActivity(){
        connect = new Firebase_Connect();
        function = new function();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report_add);

        preview_Image = findViewById(R.id.previewImage);
        viewDate = findViewById(R.id.crime_date_now);
        String currentDate = function.getCurrentDate();
        viewDate.setText(currentDate);

        progressBar = findViewById(R.id.progressBar);
        progressBar.bringToFront();

        reportSubmitBtn = findViewById(R.id.textReportSubmitBtn);

        btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCameraGallery = findViewById(R.id.btnCamera_Gallery);
        btnCameraGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsDialog();
            }
        });

        btnCrimeReport = findViewById(R.id.btnSubmit);
        btnCrimeReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                reportSubmitBtn.setVisibility(View.INVISIBLE);
                submitCrimeReport();
            }
        });
    }

    private void showOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an action")
                .setItems(new CharSequence[]{"Open Camera", "Open Gallery"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // Open Camera
                                openCamera();
                                break;
                            case 1:
                                // Open Gallery
                                openGallery();
                                break;
                        }
                    }
                })
                .setNegativeButton("Cancel", null) // Optional: Handle cancel action
                .create()
                .show();
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(this, "Camera not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE && data != null) {
                // Get the captured image from camera
                Bundle extras = data.getExtras();
                if (extras != null && extras.containsKey("data")) {
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    preview_Image.setImageBitmap(imageBitmap);
                    preview_Image.setBackgroundColor(Color.TRANSPARENT);
                }
            } else if (requestCode == GALLERY_REQUEST_CODE && data != null) {

                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    preview_Image.setImageURI(selectedImageUri);
                    preview_Image.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        }
    }

    public void submitCrimeReport(){

        TextInputLayout layoutTitle = findViewById(R.id.crime_report_title);
        TextInputLayout layoutLocation = findViewById(R.id.crime_report_location);
        TextInputLayout layoutComment = findViewById(R.id.crime_report_comment);

        title = findViewById(R.id.report_title);
        location = findViewById(R.id.report_location);
        comment = findViewById(R.id.report_comment);

        String titleText = title.getText().toString();
        String locationText = location.getText().toString();
        String commentText = comment.getText().toString();

        preview_Image = findViewById(R.id.previewImage);

        title.addTextChangedListener(new TextWatcher() {
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

        location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutLocation.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        comment.addTextChangedListener(new TextWatcher() {
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

        if(titleText.isEmpty()){
            finishActionProgressBar();
            layoutTitle.setError("* Fill in the blank");

        } else if (locationText.isEmpty()) {
            finishActionProgressBar();
            layoutLocation.setError("* Fill in the blank");
        } else if (commentText.isEmpty()) {
            finishActionProgressBar();
            layoutComment.setError("* Fill in the blank");
        }
        else {

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String userID = sharedPreferences.getString("userID", "");

            if(preview_Image.getDrawable() == null){

                String emptyImageUrl = "";

                boolean result = connect.addCrimeReport(userID, titleText, locationText, commentText, emptyImageUrl);
                if (result) {
                    finishActionProgressBar();

                    Toast.makeText(ReportAddActivity.this, "Crime report added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ReportAddActivity.this, "Failed to add crime report", Toast.LENGTH_SHORT).show();
                }
            }
            else{

                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                StorageReference imagesRef = storageRef.child("Crime_Report/" + UUID.randomUUID().toString());
                BitmapDrawable drawable = (BitmapDrawable) preview_Image.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = imagesRef.putBytes(data);

                uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful()){
                            imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String imageUrl = uri.toString();

                                    // Get userID from SharedPreferences
                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                    String userID = sharedPreferences.getString("userID", "");

                                    // Call addCrimeReport method with userID and imageUrl
                                    boolean result = connect.addCrimeReport(userID, titleText, locationText, commentText, imageUrl);
                                    if (result) {
                                        finishActionProgressBar();

                                        Toast.makeText(ReportAddActivity.this, "Crime report added successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(ReportAddActivity.this, "Failed to add crime report", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(ReportAddActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


            /*
            boolean result = connect.addCrimeReport( userID, titleText, locationText, commentText);
            if (result) {
                Toast.makeText(ReportAddActivity.this, "Crime report added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {

                Toast.makeText(ReportAddActivity.this, "Failed to add crime report", Toast.LENGTH_SHORT).show();
            }

             */
        }
    }

    public void finishActionProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
        reportSubmitBtn.setVisibility(View.VISIBLE);
    }

}