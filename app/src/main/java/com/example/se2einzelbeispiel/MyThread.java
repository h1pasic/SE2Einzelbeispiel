package com.example.se2einzelbeispiel;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Huse Pasic
 * 11805084
 */

public class MyThread implements Runnable {
    private volatile String message;
    Socket socket;
    String sentence;
    String modifiedSentenc;


    String antwort;


    @Override
    public void run() {
        try {
            socket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outFromUser;
            outFromUser = new DataOutputStream(socket.getOutputStream());
            BufferedReader inFormUser = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sentence = inFormUser.readLine();
            outFromUser.writeBytes(message + "\n");
            this.modifiedSentenc = inFromServer.readLine();
            this.antwort = modifiedSentenc;
            setAntwort(this.antwort);
            System.out.println("Server Antwort: " + modifiedSentenc);

            outFromUser.close();
            outFromUser.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }

    public void sendMsgParam(String msg){
        this.message = msg;
        run();
    }




}
