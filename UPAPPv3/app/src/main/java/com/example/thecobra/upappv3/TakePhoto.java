package com.example.thecobra.upappv3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePhoto extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnPhoto;
    private ImageView imgThumbnail;
    private String mCurrentPhotoPath, timeStamp, imageFileName;
    private ProgressBar progressBar;
    private File storageDir, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.thecobra.upappv3.R.layout.activity_take_photo);

        btnPhoto        = findViewById(com.example.thecobra.upappv3.R.id.btnCamera);
        imgThumbnail    = findViewById(com.example.thecobra.upappv3.R.id.imgThumbnail);
        progressBar     = findViewById(com.example.thecobra.upappv3.R.id.progressDownload);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePhoto = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
                if (takePhoto.resolveActivity(getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (photoFile != null){
                        Uri photoUri = FileProvider.getUriForFile(getApplicationContext(), "com.example.android.fileprovider", photoFile);
                        takePhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(takePhoto, REQUEST_IMAGE_CAPTURE);
                    }
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
            galleryAddPic();
            AsyncTask<String, Void, Bitmap> upload = new UploadTask(getApplicationContext(), imgThumbnail, progressBar).execute(mCurrentPhotoPath);
        }
    }

    private File createImageFile() throws IOException {
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "JPEG_" + timeStamp + "_";
        storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}
