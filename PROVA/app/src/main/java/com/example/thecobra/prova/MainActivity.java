package com.example.thecobra.prova;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ipServer;
    private Button btnConnect;
    private ProgressBar progressBarConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.thecobra.prova.R.layout.activity_main);

        ipServer            = findViewById(com.example.thecobra.prova.R.id.editIP);
        btnConnect          = findViewById(com.example.thecobra.prova.R.id.btnConnect);
        progressBarConnect  = findViewById(com.example.thecobra.prova.R.id.progressConnect);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ipServer.getText().toString();

                if (ip.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Insira um ip", Toast.LENGTH_SHORT).show();
                } else {
                    AsyncTask<String, Void, Boolean> connection = new ConnectionProgress(MainActivity.this, progressBarConnect, ip);
                    connection.execute(ipServer.getText().toString());
                }
            }
        });
    }
}