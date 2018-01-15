package Serveur;

import base_de_donnees.Commubdd;
import java.net.*;
import java.sql.SQLException;
import java.io.*;

public class Authentification implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String login = null, pass = null;
	public boolean authentifier = false;
	public Thread t2;
	
	public Authentification(Socket s){
		 socket = s;
		}
       
        @Override
	public void run() {
		
		Commubdd bdd = new Commubdd();
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			while(!authentifier){
				login = in.readLine();
				pass = in.readLine();
	
				if(bdd.verif_user(login, pass)){
					
					out.println("connecte");
					System.out.println(login +" vient de se connecter ");
					out.flush();
					authentifier = true;	
				}
				else {out.println("erreur"); out.flush();}
			}
			t2 = new Thread(new toClient(socket,login));
			t2.start();
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			System.err.println(login+" ne répond pas !");
                }        
	}
	
}
