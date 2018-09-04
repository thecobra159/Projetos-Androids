package com.example.cobra.upapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.SimpleMaskTextWatcher;

public class MainActivity extends AppCompatActivity {

    private EditText ipServer, portServer;
    private Button btnConnect;
    private ImageView imageView;
    private ProgressBar progressBarConnect;
    private SimpleMaskFormatter ipMaskFormatter, portMaskFormatter;
    private SimpleMaskTextWatcher ipWatcher, portWatcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipServer = findViewById(R.id.editTextIP);
        portServer = findViewById(R.id.editTextPort);
        btnConnect = findViewById(R.id.btnConnect);
        progressBarConnect = findViewById(R.id.progressConnect);

        ipMaskFormatter = new SimpleMaskFormatter("NNNN.NNNN.NNNN.NNNN");
        portMaskFormatter = new SimpleMaskFormatter("NNNN");

        ipWatcher = new SimpleMaskTextWatcher(ipServer, ipMaskFormatter);
        portWatcher = new SimpleMaskTextWatcher(portServer, portMaskFormatter);

        ipServer.addTextChangedListener(ipWatcher);
        portServer.addTextChangedListener(portWatcher);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(null);
                (new ConnectionProgress(getApplicationContext(), imageView, progressBarConnect)).execute(ipServer.getText().toString());
            }
        });
    }
}
