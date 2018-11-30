package com.example.thecobra.colors;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.androidnetworking.model.Progress;

public class GetPostTask extends AsyncTask <String, Void, Void> {
    private ProgressDialog dialog;

    public GetPostTask(ProgressDialog progressDialog) {
        this.dialog = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }
}
