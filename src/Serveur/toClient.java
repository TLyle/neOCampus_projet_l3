package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

import objet.Utilisateur;


public class toClient implements Runnable {

	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String login;
	private Thread th1, th2;
	private Commubdd bdd = new Commubdd();
	private Utilisateur user = null;
	
	public toClient(Socket s, String log){
		
		socket = s;
		login = log;
	}
	
	public void envoieUser(Utilisateur user) throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		
		
		out.println(user.getNom());
		out.flush();
		out.println(user.getPrenom());
		out.flush();
		out.println(user.getUser_name());
		out.flush();
		out.println(user.getMail());
		out.flush();
		out.println(user.getGroupe());
		out.flush();
		out.println(user.getType());
		out.flush();
	}
	
	public void run() {
		try {
		
		if(user == null) {
			user = bdd.recupUser(login);
			envoieUser(user);
		}
			
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		
		th1 = new Thread(new Reception(in,login));
		th1.start();
		th2 = new Thread(new Emission(out));
		th2.start();
		
		} catch (IOException | ClassNotFoundException | SQLException e) {
			System.err.println(login +" s'est déconnecté ");
		}
}
}

