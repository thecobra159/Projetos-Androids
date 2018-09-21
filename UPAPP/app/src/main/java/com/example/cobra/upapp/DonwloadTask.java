package com.example.cobra.upapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.IOException;

public class DonwloadTask extends AsyncTask<String, Void, Bitmap> {
    private Context context;
    private ImageView imageView;
    private ProgressBar progressBar;

    public DonwloadTask(Context context, ImageView imageView, ProgressBar progressBar) {
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

            Thread.sleep(100);

            File file2 = FileUtils.createFile(context, ".jpg");
            FileUtils.writeToFile(file2, (byte[])Singleton.getInstance().getMyFileIn().readObject());

            image = BitmapFactory.decodeFile(file2.getAbsolutePath());
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
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
        if(bitmap != null)
            imageView.setImageBitmap(bitmap);
    }
}
