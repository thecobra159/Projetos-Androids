package com.example.thecobra.prova;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ListView listViewClients;
    private List<String> listClients;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listViewClients = findViewById(R.id.listClients);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    listClients = Singleton.getInstance().getAllClients();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listClients);
            listViewClients.setAdapter(arrayAdapter);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
