package Serveur;

import base_de_donnees.Commubdd;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import objet.Message;
import objet.Ticket;

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
	
        public void receptionMessage() throws IOException, ClassNotFoundException, SQLException{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println("pret");
            out.flush();
            String expediteur = in.readLine();
            String texte = in.readLine();
            int idTicket = Integer.parseInt(in.readLine());
            bdd.creationMessageBdd(idTicket, texte, expediteur);
            out.println("message cree");
            out.flush();
        }
        
        public void receptionTicket() throws IOException, ClassNotFoundException, SQLException{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println("pret");
            out.flush();
            String titre = in.readLine();          
            String groupeE = in.readLine();            
            String groupeD = in.readLine();            
            int idTicket = bdd.newIdTicket();
            
            out.println(Integer.toString(idTicket));
            out.flush();           
            bdd.creationTicketBdd(idTicket, titre, groupeE, groupeD);
            out.println("ticket cree");
            out.flush();
        }
        
        public void envoieListeMessage(int id) throws ClassNotFoundException, SQLException, IOException{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            List<Message> l_message = bdd.getListMessage(id);
            for(int i=0; i <l_message.size(); i++){
                Message mess = l_message.get(i);
                if(in.readLine().equals("pret")){
                    out.println(mess.getEtat());
                    out.flush();
                    out.println(mess.getExpediteur());
                    out.flush();
                    out.println(mess.getTexte());
                    out.flush();
                    if(i == l_message.size())
                        out.println("fin");
                    else
                        out.println("continu");
                    out.flush();
                }
            }           
        }
        
        public void envoieTicket(Ticket tick) throws IOException, ClassNotFoundException, SQLException{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            if(in.readLine().equals("pret")){
                out.println(tick.getGroupeDestinataire());
                out.flush();
                out.println(tick.getTitre());
                out.flush();
                out.println(tick.getGroupeEmetteur());
                out.flush(); 
                out.println(Integer.toString(tick.getIdTicket()));
                out.flush();
            }
            envoieListeMessage(tick.getIdTicket());
        }
        
        public void receptionRafraichir() throws IOException, ClassNotFoundException, SQLException{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            out.println("pret");
            out.flush();
            String type = in.readLine();
            String groupe = in.readLine();
            List<String> list;
            if (type.equals("etudiant"))
                list = bdd.getListGrp("technique");
            else
                list = bdd.getListGrp("etude");
            List<Ticket> temp, l_ticket = new LinkedList<>(); 
            for(int i=0;i<list.size();i++){
                temp = bdd.getListTicket(groupe, list.get(i));
                for(Ticket tick: temp)
                    l_ticket.add(tick);
            }
            for(int i=0; i<l_ticket.size(); i++){
                int id = l_ticket.get(i).getIdTicket();
                out.println(Integer.toString(id));
                out.flush();
                if(in.readLine().equals("trouve")){
                    envoieListeMessage(id);
                } else {
                    envoieTicket(l_ticket.get(i));
                }
                if(i < list.size())
                    out.println("continu");
                else
                    out.println("fin");
                out.flush();
            }

            System.out.println("ici");
        }
        
        public boolean attenteOrdre() throws IOException, SQLException, ClassNotFoundException{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String ordre = in.readLine();
            switch(ordre){
                case "\\/ envoie ticket \\/":
                    receptionTicket();
                    break;
                case "\\/ envoie message \\/":
                    receptionMessage();
                    break;
                case "\\/ rafraichir \\/":
                    receptionRafraichir();
                    break;
                case "\\/ deconnexion \\/":
                    return true;
            }
            return false;
        }
        
        @Override
        public void run(){
            boolean deco = false;
            try{
                if(user == null) {
                    user = bdd.recupUser(login);
                    envoieUser(user);
		}
                while(! deco){
                    deco = attenteOrdre();
                }
                socket.close();
                System.out.println(login +" s'//est déconnecté");
            }catch (IOException | ClassNotFoundException | SQLException e) {
                System.err.println(login +" s'est déconnecté ");
            }
        }
}

