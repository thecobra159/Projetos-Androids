package com.example.cobra.upapp;

import java.io.IOException;
import java.net.Socket;

public class Singleton {
    private static Singleton ourInstance = null;

    public Singleton() {

    }

    public static Singleton getInstance() throws IOException {
        if (ourInstance == null) {
            ourInstance = new Singleton();
        }
        return ourInstance;
    }

    public boolean connect(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
