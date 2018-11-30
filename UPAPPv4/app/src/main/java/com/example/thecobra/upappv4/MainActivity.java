package com.example.thecobra.upappv4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText ipServer, portServer;
    private Button btnConnect;
    private ProgressBar progressConnect;
    private List<String> ipPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipServer            = findViewById(R.id.editTextIP);
        portServer          = findViewById(R.id.editTextPort);
        btnConnect          = findViewById(R.id.btnConnect);
        progressConnect     = findViewById(R.id.progressConnect);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip   = ipServer.getText().toString();
                String port = portServer.getText().toString();

                if (ip.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Insira um ip", Toast.LENGTH_SHORT).show();
                } else if (port.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Insira uma porta", Toast.LENGTH_SHORT).show();
                } else if (ip.trim().isEmpty() || port.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
                } else {
                    ipPort = new ArrayList<>();
                    ipPort.add(ip);
                    ipPort.add(port);

                    AsyncTask<String, Void, Boolean> connection = new ConnectionProgress(MainActivity.this, progressConnect, (ArrayList<String>) ipPort);
                    connection.execute(ipServer.getText().toString(), portServer.getText().toString());
                }
            }
        });
    }
}