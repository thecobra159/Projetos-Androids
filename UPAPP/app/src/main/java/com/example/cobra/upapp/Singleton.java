package com.example.cobra.upapp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Singleton {
<<<<<<< HEAD
    private static Singleton myInstance = null;
    private Socket socket;
=======
    private static Singleton ourInstance = null;
<<<<<<< HEAD
    private static Socket socket;
=======
>>>>>>> 5985d8f1dc43bebee9aeb03729846b9325bf34d0

    public Singleton() {

    }
>>>>>>> 41bba0db3002e91b8b0e39897c655f6f857c0faf

    public static Singleton getInstance() throws IOException {
        if (myInstance == null) {
            myInstance = new Singleton();
        }
        return myInstance;
    }

    public boolean connect(String ip, int port) {
        try {
<<<<<<< HEAD
            socket = new Socket(ip, port);
            socket.connect(new InetSocketAddress(ip, port));
=======
<<<<<<< HEAD
            socket = new Socket(ip, port);
=======
            Socket socket = new Socket(ip, port);
>>>>>>> 5985d8f1dc43bebee9aeb03729846b9325bf34d0
>>>>>>> 41bba0db3002e91b8b0e39897c655f6f857c0faf
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
<<<<<<< HEAD

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
=======
<<<<<<< HEAD

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        Singleton.socket = socket;
    }
=======
>>>>>>> 5985d8f1dc43bebee9aeb03729846b9325bf34d0
>>>>>>> 41bba0db3002e91b8b0e39897c655f6f857c0faf
}
