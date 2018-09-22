package com.example.cobra.upapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class TakePhoto extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnPhoto;
    private ImageView imgThumbnail;
    private String mCurrentPhotoPath;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        btnPhoto        = findViewById(R.id.btnCamera);
        imgThumbnail    = findViewById(R.id.imgThumbnail);
        progressBar     = findViewById(R.id.progressDownload);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePhoto = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
                if (takePhoto.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePhoto, REQUEST_IMAGE_CAPTURE);
//                    try {
//                        File photoFile = FileUtils.createFile(getApplicationContext(), ".jpg");
//                        mCurrentPhotoPath = photoFile.getAbsolutePath();
//
//                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), "br.com.cobra.upapp.fileprovider", photoFile);
//                        takePhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");
            imgThumbnail.setImageBitmap(image);
//            Bitmap imageBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
//            imgThumbnail.setImageBitmap(imageBitmap);
            AsyncTask<String, Void, Bitmap> upload = new UploadTask(getApplicationContext(), imgThumbnail, progressBar).execute(mCurrentPhotoPath);
        }
    }
}
