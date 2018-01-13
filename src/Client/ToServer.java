package Client;

import java.io.*;
import java.net.*;
import objet.Ticket;

import objet.TypeUtilisateur;
import objet.Utilisateur;


public class ToServer implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Thread th1, th2;
	private Utilisateur user = null;
	
	public ToServer(Socket s){
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
	
        public void envoieTicket(){
            
        }
        
        public void envoieMessage(){
            
        }
        
        public void rafraichir(){
            
        }
        //TODO finir envoie d'ordre
        public void envoieOrdre(String ordre) throws IOException{
            out = new PrintWriter(socket.getOutputStream());
            
            out.println(ordre);
            out.flush();
            out.close();
        }
        
        public boolean envoieMessage(String message, Utilisateur user, int idTicket) throws IOException{
            boolean ok;
            
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("\\/ envoie message \\/");
            out.flush();
            out.println(user.getUser_name());
            out.flush();
            out.println(message);
            out.flush();
            out.println(idTicket);
            out.flush();
            ok = in.readLine().equals("message cree");
            
            out.close();
            in.close();
            return ok;
        }
        
        public boolean envoieTicket(String titre, String groupeE, String groupeD, String message, Utilisateur user) throws IOException{
            boolean ok = false;
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("\\/ envoie ticket \\/");
            out.flush();
            out.println(titre);
            out.flush();
            out.println(groupeE);
            out.flush();
            out.println(groupeD);
            out.flush();
            int idticket = in.read();
            boolean ticketRecu = in.readLine().equals("ticket cree");
            
            user.addTicket(new Ticket(titre, groupeE, groupeD, idticket));
            if(envoieMessage(message, user, idticket) && ticketRecu)
                ok = true;
            
            in.close();
            out.close();
            return ok;
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
			System.err.println("Le serveur distant s'est d�connect� !");
		}
	}

}

