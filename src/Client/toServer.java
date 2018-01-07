package Client;

import java.io.*;
import java.net.*;

import objet.TypeUtilisateur;
import objet.Utilisateur;


public class toServer implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Thread th1, th2;
	private Utilisateur user = null;
	
	public toServer(Socket s){
		socket = s;
	}
	
	public Utilisateur receptionUser() throws IOException {
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String nom = in.readLine();
		String prenom = in.readLine();
		String user_name = in.readLine();
		String mail = in.readLine();
		String groupe = in.readLine();
		String type = in.readLine();
		Utilisateur user = null;
		
		switch(type) {
		case "etudiant":
			user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.etudiant);
			System.out.println(user);
			break;
		case "professeur":
			user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.professeur);
			break;
		case "administrateur":
			user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.administrateur);
			break;
		}
		return user;
	}
	
	public void run() {
		try {
			if(user == null) {
				user = receptionUser();
				System.out.println(user);
			}
					
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			th1 = new Thread(new Emission(out));
			th1.start();
			th2 = new Thread(new Reception(in));
			th2.start();
		
		   
		    
		} catch (IOException e) {
			System.err.println("Le serveur distant s'est déconnecté !");
		}
	}

}

