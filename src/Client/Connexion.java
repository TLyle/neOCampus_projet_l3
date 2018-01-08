package Client;

import java.net.*;
import java.util.Scanner;

import objet.Utilisateur;

import java.io.*;

public class Connexion implements Runnable {

	private Socket socket = null;
	public static Thread th;
	public static String login, pass;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Scanner sc = null;
	private boolean connect = false;
	public Utilisateur user;	
	
	public Connexion(Socket s){
		
		socket = s;
	}
	
        public boolean authen(String username, String password) throws IOException{
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
                        
            out.println(username);
            out.flush();
            
            out.println(password);
            out.flush();
            
            return (in.readLine().equals("connecte"));
        }
        
	public void run() {
		
		try {
			
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
		sc = new Scanner(System.in);
	
		
		while(!connect ){
			System.out.println(in.readLine());
			login = sc.nextLine();
			out.println(login);
			out.flush();
			
			System.out.println(in.readLine());
			pass = sc.nextLine();
			out.println(pass);
			out.flush();
			
			if(in.readLine().equals("connecte")){
				System.out.println("Je suis connect� "); 
				connect = true;
			} else {
				System.err.println("Vos informations sont incorrectes "); 
			}
		}
			
			th = new Thread(new toServer(socket));
			th.start();
		
		} catch (IOException e) {
			
			System.err.println("Le serveur ne r�pond plus ");
		}
	}

}
