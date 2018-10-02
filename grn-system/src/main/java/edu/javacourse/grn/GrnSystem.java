package edu.javacourse.grn;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class GrnSystem {

    public static void main(String[] args) {
        GrnSystem gm = new GrnSystem();
        gm.start();
    }

    private void start() {
        System.out.println("GrnSystem started");
        try {
            ServerSocket ses = new ServerSocket(7777);
            while (true) {
                Socket socket = ses.accept();
                RequestHandler rh = new RequestHandler(socket);
                new Thread(rh).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
