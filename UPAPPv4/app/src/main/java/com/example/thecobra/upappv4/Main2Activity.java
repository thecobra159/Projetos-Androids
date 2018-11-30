package com.example.thecobra.upappv4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ListView listPreview;
    private Button btnUpload, btnDownload;
    private ImageView imgPreview;
    private ArrayAdapter<File> adapter;
    private List<File> values;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listPreview     = findViewById(R.id.listViewImages);
        btnUpload       = findViewById(R.id.btnUpload);
        btnDownload     = findViewById(R.id.btnDownload);
        imgPreview      = findViewById(R.id.imagePreview);


        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    if (getImages() != null)
                        values  = getImages();
                    else
                        values = new ArrayList<>();
                    adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, values);
                    listPreview.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TakePhoto.class);
                startActivity(intent);
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intent = new IntentIntegrator(activity);
                intent.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intent.setPrompt("Câmera Scan");
                intent.setCameraId(0);
                intent.setBeepEnabled(true);
                intent.setBarcodeImageEnabled(true);
                intent.initiateScan();
            }
        });

        listPreview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() != null){
                Uri imageUri = data.getData();
                Bitmap bitmap;
                alert(result.getContents());
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imgPreview.setImageBitmap(bitmap);

                } catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                alert("Scan cancelado!");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void alert(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public List<File> getImages() throws IOException {
        List<File> images = new ArrayList<>();
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(String.valueOf(getComponentName()), ".jpg", storageDir);
        images.add(image);
        return images;
    }
}
