package com.example.thecobra.upappv4;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ConnectionProgress extends AsyncTask<String, Void, Boolean> {
    private MainActivity context;
    private ProgressBar progressConnect;
    private List<String> ipPort;
    private boolean connect = false;

    public ConnectionProgress(MainActivity context, ProgressBar progressConnect, List<String> ipPort) {
        this.context = context;
        this.progressConnect = progressConnect;
        this.ipPort = ipPort;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            if (Singleton.getInstance().connect(ipPort.get(0), Integer.parseInt(ipPort.get(1)))){
                Thread.sleep(2000);
                connect = true;
            } else {
                connect = false;
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            connect = false;
        }
        return connect;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressConnect.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        progressConnect.setVisibility(View.INVISIBLE);

        if (!connect) {
            Toast.makeText(context, "Não foi possível estabelecer uma conexão!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Conexão estabelecida com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Main2Activity.class);
            context.startActivity(intent);
            context.finish();
        }
    }
}
