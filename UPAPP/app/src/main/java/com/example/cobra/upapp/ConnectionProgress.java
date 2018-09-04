package com.example.cobra.upapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ConnectionProgress extends AsyncTask<String, Void, Bitmap> {
    private Context context;
    private ImageView imageView;
    private ProgressBar progressBarConnect;

    public ConnectionProgress(Context context, ImageView imageView, ProgressBar progressBarConnect) {
        this.context = context;
        this.imageView = imageView;
        this.progressBarConnect = progressBarConnect;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBarConnect.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        progressBarConnect.setVisibility(View.INVISIBLE);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap image = null;
        try {
            String str_url = strings[0];
            URL url = new URL(str_url);
            InputStream inputStream = url.openStream();
            image = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
