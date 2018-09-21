package com.example.cobra.upapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Singleton {
    private static Singleton myInstance = null;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Singleton(){}

    public static Singleton getInstance() throws IOException {
        if (myInstance == null) {
            myInstance = new Singleton();
        }
        return myInstance;
    }

    public boolean connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObjectInputStream getMyFileIn() throws IOException {
        return in = new ObjectInputStream(socket.getInputStream());
    }

    public ObjectOutputStream sendMyFileOut() throws IOException {
        return out = new ObjectOutputStream(socket.getOutputStream());
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
}
