package com.example.thecobra.takephoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

class DownloadTask extends AsyncTask<String, Void, Bitmap> {
    private final String ip;
    private final int port;
    private Context context;
    private ImageView imageView;
    private ProgressBar progressBar;
    public DownloadTask(Context context, ImageView imageView, ProgressBar progress, String ip, int port) {
        this.context = context;
        this.imageView = imageView;
        this.progressBar = progress;
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        progressBar.setVisibility(View.INVISIBLE);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected Bitmap doInBackground(String... files) {
        Bitmap image = null;
        try {
            Socket socket = null;
            socket = new Socket(ip, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            File file1 = new File(files[0]);
            out.writeObject(FileUtils.readFromFile(file1));

            Thread.sleep(100);

            File file2 = FileUtils.createFile(context, ".jpg");
            FileUtils.writeToFile(file2, (byte[])in.readObject());
            image = BitmapFactory.decodeFile(file2.getAbsolutePath());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return image;
    }
}
