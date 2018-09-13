package com.example.cobra.upapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionProgress extends AsyncTask<String, Void, Boolean> {
    private Context context;
    private ProgressBar progressBarConnect;
    private List<String> ipPort;
    private boolean connect = false;

    public ConnectionProgress(Context context, ProgressBar progressBarConnect, ArrayList<String> ipPort) {
        this.context = context;
        this.progressBarConnect = progressBarConnect;
        this.ipPort = ipPort;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    if (Singleton.getInstance().connect(ipPort.get(0), Integer.parseInt(ipPort.get(1))))
                        connect = true;
                    else
                        connect = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        return connect;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBarConnect.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        progressBarConnect.setVisibility(View.INVISIBLE);

        if (!connect)
            Toast.makeText(context, "Não foi possível estabelecer uma conexão!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Conexão estabelecida com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
