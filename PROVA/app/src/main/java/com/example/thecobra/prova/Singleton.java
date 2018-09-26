package com.example.thecobra.prova;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton myInstance = null;
    private static final int PORT = 5000;
    private Socket socket;
    private List<String> ipClients = new ArrayList<>();

    public Singleton(){}

    public static Singleton getInstance() throws IOException {
        if (myInstance == null) {
            myInstance = new Singleton();
        }
        return myInstance;
    }

    public boolean connect(String ip) {
        try {
            socket = new Socket(ip, PORT);
            ipClients.add(String.valueOf(socket.getInetAddress()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getAllClients(){
        if(socket.isClosed()){
            ipClients.remove(socket.getInetAddress());
        }
        return ipClients;
    }
}