package com.example.thecobra.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private String mCurrtentPhotoPath;
    private Button btnTakePhoto;
    private ImageView imageView, imgThumbnail;
    private EditText txtServer, txtPort;
    private ProgressBar progress;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTakePhoto = findViewById(R.id.btnPicture);
        imageView = findViewById(R.id.imgView);
        imgThumbnail = findViewById(R.id.imgThumbnail);
        txtServer = findViewById(R.id.txtServer);
        txtPort = findViewById(R.id.txtPort);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePìctureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePìctureIntent.resolveActivity(getPackageManager()) != null){
                    File photoFile = null;
                    try {
                        photoFile = FileUtils.createFile(getApplicationContext(), ".jpg");
                        mCurrtentPhotoPath = photoFile.getAbsolutePath();

                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), "br.com.example.thecobra159.takephoto.fileprovider", photoFile);
                        takePìctureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePìctureIntent, REQUEST_IMAGE_CAPTURE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bitmap imageBitmap = BitmapFactory.decodeFile(mCurrtentPhotoPath);
            imgThumbnail.setImageBitmap(imageBitmap);
            imageView.setImageBitmap(null);
            String ip = txtServer.getText().toString();
            int port = Integer.parseInt(txtPort.getText().toString());
            (new DownloadTask(this, imageView, progress, ip, port)).execute(mCurrtentPhotoPath);
        }
    }
}
