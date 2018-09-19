package com.example.cobra.upapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.net.Socket;

public class TakePhoto extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnPhoto, btnSave;
    private EditText photoName;
    private ImageView imgPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        btnPhoto = findViewById(R.id.btnCamera);
        btnSave = findViewById(R.id.btnSave);

        photoName = findViewById(R.id.editTextPhotoName);

        imgPreview = findViewById(R.id.imgPreview);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePhoto = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
                if(takePhoto.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePhoto, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Socket socket = Singleton.getInstance().getSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgPreview.setImageBitmap(imageBitmap);

            String name = photoName.getText().toString();

            if(!name.trim().isEmpty()){
                btnSave.setEnabled(true);
            }
        }
    }
}
