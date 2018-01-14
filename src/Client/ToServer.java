package Client;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import objet.Message;
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
	
	public Utilisateur receptionUser() throws IOException, ClassNotFoundException {
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
                    break;
            case "professeur":
                    user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.professeur);
                    break;
            case "administrateur":
                    user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.administrateur);
                    break;
            case "technique":
                    user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.technique);
                    break;
            }
            return user;
	}
	
        public void receptionDesMessages(Utilisateur user, Ticket ticket) throws IOException{
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("\\/ message \\/");
            out.flush();
            String fin = in.readLine();
            while(! fin.equals("fin")){
                out.println("pret");
                out.flush();
                String etat = in.readLine();
                String expediteur = in.readLine();
                String texte = in.readLine();
                ticket.addMessage(new Message(expediteur, etat, texte));
                fin = in.readLine();
            }
        }
        
        public void receptionDuTickets(Utilisateur user) throws IOException{
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
            out.println("\\/ ticket \\/");
            out.flush();
            out.println("pret");
            out.flush();
            String groupeD = in.readLine();
            String titre = in.readLine();
            String groupeE = in.readLine();
            int idTicket = Integer.parseInt(in.readLine());
            Ticket tick = new Ticket(titre, groupeE, groupeE, idTicket);
            user.addTicket(tick);
            receptionDesMessages(user, tick);
        }
        
        public void rafraichir(Utilisateur user) throws IOException{
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("\\/ rafraichir \\/");
            out.flush();
            if(in.readLine().equals("pret")){
                out.println(user.getType());
                out.flush();
                out.println(user.getGroupe());
                out.flush();
            }
            String ok = "non";
            int idAChercher;
            while(ok.equals("non")){
                idAChercher = Integer.parseInt(in.readLine());
                Ticket tickTrouve = user.hasTicket(idAChercher);
                if(tickTrouve == null){
                    receptionDuTickets(user);
                }else{
                    receptionDesMessages(user, tickTrouve);
                }
            }
        }
        
        public boolean envoieMessage(String message, Utilisateur user, int idTicket) throws IOException{
            boolean ok;
            
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("\\/ envoie message \\/");
            out.flush();
            if(in.readLine().equals("pret")){
                out.println(user.getUser_name());
                out.flush();
                out.println(message);
                out.flush();
                out.println(Integer.toString(idTicket));
                out.flush();
            }
            ok = in.readLine().equals("message cree");
            return ok;
        }
        
        public void deco() throws IOException{
            out = new PrintWriter(socket.getOutputStream());
            out.println("\\/ deconnexion \\/");
            out.flush();
        }
        
        public boolean envoieTicket(String titre, String groupeE, String groupeD, String message, Utilisateur user) throws IOException{
            boolean ok = false;
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("\\/ envoie ticket \\/");
            out.flush();
            if(in.readLine().equals("pret")){
                out.println(titre);
                out.flush();
                out.println(groupeE);
                out.flush();
                out.println(groupeD);
                out.flush();
            }
            int idticket = Integer.parseInt(in.readLine());
            
            boolean ticketRecu = in.readLine().equals("ticket cree");
            
            user.addTicket(new Ticket(titre, groupeE, groupeD, idticket));
            if(envoieMessage(message, user, idticket) && ticketRecu){
                ok = true;
            }
            return ok;
        }
        
	public void run() {
		try {
			if(user == null) {
				user = receptionUser();
			}
					
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			th1 = new Thread(new Emission(out));
			th1.start();
			th2 = new Thread(new Reception(in));
			th2.start();
		
		   
		    
		} catch (IOException e) {
			System.err.println("Le serveur distant s'est d�connect� !");
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(ToServer.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}
