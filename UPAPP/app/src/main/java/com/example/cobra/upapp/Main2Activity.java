package com.example.cobra.upapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private ListView listMini;
    private Button btnUp, btnDown;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listMini = findViewById(R.id.listView);
        btnUp = findViewById(R.id.btnUpload);
        btnDown = findViewById(R.id.btnDownload);

        values = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        listMini.setAdapter(adapter);


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TakePhoto.class);
                startActivity(intent);
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intent = new IntentIntegrator((Activity) getApplicationContext());
                intent.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intent.setPrompt("C�mera Scan");
                intent.setCameraId(0);
                intent.initiateScan();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() != null){

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
}
