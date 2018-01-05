package Serveur;

import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null, login = null;
	
	public Reception(BufferedReader in, String login){
		
		this.in = in;
		this.login = login;
	}
	
	public void run() {
		
		while(true){
	        try {
	        	
			message = in.readLine();
			if(!message.equals("quit"))
				System.out.println(login+" : "+message);
			else
				System.out.println("tentative de deco");
			
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
