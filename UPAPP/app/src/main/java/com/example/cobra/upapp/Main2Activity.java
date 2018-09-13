package com.example.cobra.upapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ListView listMini;
    private Button btnUp, btnDown;
    private ArrayAdapter adapter;
    private List values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listMini = findViewById(R.id.listView);
        btnUp = findViewById(R.id.btnUpload);
        btnDown = findViewById(R.id.btnDownload);

        try {
            values = null;
            adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, values);
            listMini.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
