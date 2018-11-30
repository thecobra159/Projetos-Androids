package com.example.thecobra.upappv4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class UploadTask extends AsyncTask<String, Void, Bitmap> {
    private Activity context;
    private ImageView imageView;
    private ProgressBar progressBar;

    public UploadTask(Activity context, ImageView imageView, ProgressBar progressBar) {
        this.context = context;
        this.imageView = imageView;
        this.progressBar = progressBar;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap image = null;

        try{
            File file = new File(strings[0]);
            Singleton.getInstance().sendMyFileOut().writeObject(FileUtils.readFromFile(file));

            Thread.sleep(2000);

            image = BitmapFactory.decodeFile(file.getAbsolutePath());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(context, "Upload feito com sucesso!", Toast.LENGTH_SHORT).show();
        context.finish();
    }
}