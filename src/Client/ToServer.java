package Client;

import base_de_donnees.Commubdd;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objet.Message;
import objet.Ticket;

import objet.TypeUtilisateur;
import objet.Utilisateur;


public class ToServer{// implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Utilisateur user = null;
	
	public ToServer(Socket s) throws IOException{
		socket = s;
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
        
	public Utilisateur receptionUser() throws IOException, ClassNotFoundException {
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
            String fin = "non";
            while(! fin.equals("fin")){
                out.println("pret");
                out.flush();
                String etat = in.readLine();
                String expediteur = in.readLine();
                String texte = in.readLine();
                ticket.addMessage(new Message(expediteur, etat, texte));
                fin = in.readLine();
            }
            user.addTicket(ticket);
        }
        
        public void receptionDuTickets(Utilisateur user) throws IOException{
            out.println("pret");
            out.flush();
            String groupeD = in.readLine();
            String titre = in.readLine();
            String groupeE = in.readLine();
            int idTicket = Integer.parseInt(in.readLine());
            
            Ticket tick = new Ticket(titre, groupeE, groupeE, idTicket);
            receptionDesMessages(user, tick);           
            user.addTicket(tick);
        }
        
        public void rafraichir(Utilisateur user) throws IOException{
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
                    out.println("trouve");
                    out.flush();
                    receptionDuTickets(user);
                }else{
                    out.println("pas trouve");
                    out.flush();
                    receptionDesMessages(user, tickTrouve);
                }
                ok = in.readLine();
            }
        }
        
        /**
         * Cette fonction est la deuxième version du rafraichissement parce que
         * la première ne fonctionnait pas
         * @param user
         * @throws ClassNotFoundException
         * @throws SQLException 
         */
        public void rafraichir2(Utilisateur user) throws ClassNotFoundException, SQLException{
            Commubdd bdd = new Commubdd();
            List<String> list_groupe;
            if(user.getType().equals("etudiant"))
                list_groupe = bdd.getListGrp("technique");
            else
                list_groupe = bdd.getListGrp("etude");
            List<Ticket> list_ticket;
            for(String grp: list_groupe){
                list_ticket = bdd.getListTicket(user.getGroupe(), grp);
                for(Ticket tick: list_ticket)
                    user.addTicket(tick);
                list_ticket = bdd.getListTicket(grp, user.getGroupe());
                for(Ticket tick: list_ticket)
                    user.addTicket(tick);
            }
        }
        
        public boolean envoieMessage(String message, Utilisateur user, int idTicket) throws IOException{
            boolean ok;
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
}

