package com.example.cobra.upapp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Singleton {
    private static Singleton myInstance = null;
    private Socket socket;

    public static Singleton getInstance() throws IOException {
        if (myInstance == null) {
            myInstance = new Singleton();
        }
        return myInstance;
    }

    public boolean connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            socket.connect(new InetSocketAddress(ip, port));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
