package Serveur;

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
	public void run() {
		
		Commubdd bdd = new Commubdd();
		
		try {
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			while(!authentifier){
				
				out.println("Entrez votre login :");
				out.flush();
				login = in.readLine();
				
				
				out.println("Entrez votre mot de passe :");
				out.flush();
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
			
		} catch (IOException e) {
			
			System.err.println(login+" ne répond pas !");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
