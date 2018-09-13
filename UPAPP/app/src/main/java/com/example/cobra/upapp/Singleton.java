package com.example.cobra.upapp;

import java.io.IOException;
import java.net.Socket;

public class Singleton {
    private static Singleton ourInstance = null;
<<<<<<< HEAD
    private static Socket socket;
=======
>>>>>>> 5985d8f1dc43bebee9aeb03729846b9325bf34d0

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
<<<<<<< HEAD
            socket = new Socket(ip, port);
=======
            Socket socket = new Socket(ip, port);
>>>>>>> 5985d8f1dc43bebee9aeb03729846b9325bf34d0
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
<<<<<<< HEAD

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        Singleton.socket = socket;
    }
=======
>>>>>>> 5985d8f1dc43bebee9aeb03729846b9325bf34d0
}
