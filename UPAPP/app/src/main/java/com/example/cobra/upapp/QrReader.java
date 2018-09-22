package com.example.cobra.upapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class QrReader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_reader);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView myImageView = findViewById(R.id.imgview);
                Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.puppy);
                myImageView.setImageBitmap(bitmap);

                BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_MODE).build();
            }
        });
    }
}
