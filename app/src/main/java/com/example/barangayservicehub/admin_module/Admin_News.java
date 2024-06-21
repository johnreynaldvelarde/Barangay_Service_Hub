package com.example.barangayservicehub.admin_module;

import android.annotation.SuppressLint;
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

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.example.barangayservicehub.core.function;
import com.example.barangayservicehub.report_module.ReportAddActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class Admin_News extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1001;

    private static final int GALLERY_REQUEST_CODE = 1002;

    final Firebase_Connect connect;
    final function function;

    public TextView addNewsTitle, addNewsArticle, btnTextAddNews, viewNewsDate;

    public FrameLayout btnFrame_Add_News;

    public ImageView btnBack, preview_news;
    public MaterialSwitch btnToogle;

    public ImageButton btnCameraGallery;

    ProgressBar progressBar;

    public Admin_News(){
        connect = new Firebase_Connect();
        function = new function();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_news);

        progressBar = findViewById(R.id.progressBar);
        btnTextAddNews = findViewById(R.id.btnTextNewsSubmit);
        btnBack = findViewById(R.id.btnBackArrow);
        viewNewsDate = findViewById(R.id.add_news_date_now);
        btnFrame_Add_News = findViewById(R.id.btnFrameNewsSubmit);

        preview_news = findViewById(R.id.preview_news);
        btnCameraGallery = findViewById(R.id.btnCamera_Gallery);

        btnCameraGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsDialog();
            }
        });


        String current = function.getCurrentDate();
        viewNewsDate.setText(current);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFrame_Add_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                btnTextAddNews.setVisibility(View.INVISIBLE);
                addBarangayNews();

            }
        });

    }

    public void  addBarangayNews(){

        TextInputLayout layoutNewsTitle = findViewById(R.id.layout_news_title);
        TextInputLayout layoutNewsArticle = findViewById(R.id.layout_news_article);


        addNewsTitle = findViewById(R.id.news_title);
        addNewsArticle = findViewById(R.id.news_article);

        String title = addNewsTitle.getText().toString();
        String article = addNewsArticle.getText().toString();

        preview_news = findViewById(R.id.preview_news);

        addNewsTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutNewsTitle.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addNewsArticle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutNewsArticle.setError(null);

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(title.isEmpty()){
            function.finishActionProgressBar(progressBar, btnTextAddNews);
            layoutNewsTitle.setError("* Fill in the blank");

        } else if (article.isEmpty()) {
            function.finishActionProgressBar(progressBar, btnTextAddNews);
            layoutNewsArticle.setError("* Fill in the blank");
        }
        else {

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String userID = sharedPreferences.getString("userID", "");

            btnToogle = findViewById(R.id.toogleImportant);
            int toggleImportantValue = btnToogle.isChecked() ? 1 : 0;

            if(preview_news.getDrawable() == null){

                String emptyImageUrl = "";

                boolean result = connect.addBarangayNews(userID, title, article, emptyImageUrl, toggleImportantValue);
                if (result) {

                    function.finishActionProgressBar(progressBar, btnTextAddNews);
                    Toast.makeText(Admin_News.this, "Barangay news added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Admin_News.this, "Failed to add news", Toast.LENGTH_SHORT).show();
                }


            }else {

                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                StorageReference imagesRef = storageRef.child("Barangay_News/" + UUID.randomUUID().toString());
                BitmapDrawable drawable = (BitmapDrawable) preview_news.getDrawable();
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

                                    boolean result = connect.addBarangayNews(userID, title, article, imageUrl, toggleImportantValue);
                                    if (result) {

                                        function.finishActionProgressBar(progressBar, btnTextAddNews);
                                        Toast.makeText(Admin_News.this, "Barangay news added successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(Admin_News.this, "Failed to add news", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(Admin_News.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }

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
                    preview_news.setImageBitmap(imageBitmap);
                    preview_news.setBackgroundColor(Color.TRANSPARENT);
                }
            } else if (requestCode == GALLERY_REQUEST_CODE && data != null) {

                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    preview_news.setImageURI(selectedImageUri);
                    preview_news.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        }
    }
}