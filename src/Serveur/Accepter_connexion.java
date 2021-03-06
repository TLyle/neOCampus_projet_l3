package Serveur;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Accepter_connexion implements Runnable{

    private ServerSocket socketserver = null;
    private Socket socket = null;

    public Thread t1;
    public Accepter_connexion(ServerSocket ss){
        socketserver = ss;
    }

    @Override
    public void run() {

        try {
            while(true){

                socket = socketserver.accept();

                t1 = new Thread(new Authentification(socket));
                t1.start();

            }
        } catch (IOException e) {
            System.err.println("Erreur serveur");
        }

    }
}

