package base_de_donnees;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import objet.Message;
import objet.Ticket;

import objet.TypeUtilisateur;
import objet.Utilisateur;

public class Commubdd {
	
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/projet";
	private static final String ident_user = "root";
	private static final String ident_mdp = "";
	
	public boolean verif_user(String user, String mdp) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp);
                Boolean retour;
            try (Statement state = conn.createStatement()) {
                String com = "SELECT * FROM utilisateur";
                    try (ResultSet result = state.executeQuery(com)) {
                        retour = false;
                        while(result.next() && !retour) {
                            String bdd_user = result.getObject(1).toString();
                            String bdd_mdp = result.getObject(2).toString();
                            if(user.equals(bdd_user) && mdp.equals(bdd_mdp)) {
                                retour = true;
                            }
                        }   }
            }
		return retour;
	}
	
	public Utilisateur recupUser(String login) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
                Utilisateur user;
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                ResultSet result = state.executeQuery("SELECT * FROM utilisateur");
                Boolean ok = false;
                while(!ok) {
                    result.next();
                    if(result.getObject(1).toString().equals(login))
                        ok = true;
                }
                String nom = result.getObject(5).toString();
                String prenom = result.getObject(6).toString();
                String user_name = result.getObject(1).toString();
                String mail = result.getObject(7).toString();
                String groupe = result.getObject(4).toString();
                String type = result.getObject(3).toString();
                user = null;
                switch(type) {
                    case "Etudiant":
                        user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.etudiant);
                        break;
                    case "Professeur":
                        user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.professeur);
                        break;
                    case "Administrateur":
                        user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.administrateur);
                        break;
                    case "Service technique":
                        user = new Utilisateur(nom, prenom, user_name, mail, groupe, TypeUtilisateur.technique);
                        break;
                }
            }
            
            return user;
	}
        
        /**
         * 
         * @param "etude" => liste groupe technique
         * @return liste de String de groupe
         * @throws ClassNotFoundException
         * @throws SQLException 
         */
        public List getListGrp(String categorie) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            List<String> liste = new LinkedList<>();
            String commande;
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                if(categorie.equals("etude"))
                    commande = "SELECT * FROM groupe WHERE (Categorie = 'Etude')";
                else
                    commande = "SELECT * FROM groupe WHERE (Categorie = 'Technique')";
                ResultSet result = state.executeQuery(commande);
                
                while(result.next())
                    liste.add(result.getObject(1).toString());
            }
            return liste;
        }
        
        public Message extraireMessage(ResultSet result) throws SQLException{
            Message mess;
 
            String expediteur = result.getObject(2).toString();
            String etat = result.getObject(3).toString();
            String texte = result.getObject(4).toString();
            mess = new Message(expediteur, etat, texte);
 
            return mess;
        }

        public void ajoutMessage(Ticket ticket) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
 
            List<Ticket> liste = new LinkedList<>();
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                
                String commande = "SELECT * FROM message WHERE (Ticket = "+ ticket.getIdTicket() +")";
                ResultSet result = state.executeQuery(commande);
                
                while(result.next()){
                    Message mess = extraireMessage(result);
                    ticket.addMessage(mess);
                }
            }
        }
 
        public Ticket extraireTicket(ResultSet result) throws SQLException, ClassNotFoundException{
            Ticket tick;
            
            int idTicket = result.getInt(1);
            String titre = result.getObject(2).toString();
            String groupeDestinataire = result.getObject(4).toString();
            String groupeEmetteur = result.getObject(3).toString();
            tick = new Ticket(titre, groupeEmetteur, groupeDestinataire, idTicket);
            ajoutMessage(tick);
            
            return tick;
        }
        
        public List getListTicket(String grpEmetteur, String grpDestinataire) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            List<Ticket> liste = new LinkedList<>();
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                
                String commande = "SELECT * FROM ticket WHERE (GroupeSortant = '"+grpEmetteur+"') AND (GroupeDestinataire = '"+grpDestinataire+"')";
                ResultSet result = state.executeQuery(commande);
                
                while(result.next()){
                    liste.add(extraireTicket(result));
                }
            }
            return liste;
        }
        
        public int newIdTicket() throws ClassNotFoundException, SQLException{
            int id = 1;
            boolean ok = false;
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                
                String commande = "SELECT * FROM ticket";
                ResultSet result = state.executeQuery(commande);
                
                while(result.next() && !ok){
                    if(id == result.getInt(1))
                        id ++;
                    else
                        ok = true;
                }
            }
            return id;
        }
        
        public void creationTicketBdd(int id, String titre, String groupeE, String groupeD) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                
                String commande = "insert into ticket values ('"+id+"', '"+titre+"', '"+groupeE+"', '"+groupeD+"')";
                state.executeUpdate(commande);
            }
        }
        
        public void creationMessageBdd(int id, String texte, String expediteur) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                
                String commande = "insert into message values ('"+id+"', '"+expediteur+"','Recu', '"+texte+"')";
                state.executeUpdate(commande);
            }
        }
        
        public List getListMessage(int idTicket) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            List<Message> liste = new LinkedList<>();
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                
                String commande = "SELECT * FROM message WHERE (Ticket = '"+idTicket+"')";
                ResultSet result = state.executeQuery(commande);
                
                while(result.next()){
                    String expediteur = result.getObject(2).toString();
                    String etat = result.getObject(3).toString();
                    String texte = result.getObject(4).toString();
                    liste.add(new Message(expediteur, etat, texte));
                }
            }
            return liste;
        }
        
        public boolean grpExiste(String groupe) throws SQLException, ClassNotFoundException{
            Class.forName(driver);
            boolean trouve = false;
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                
                String commande = "SELECT * FROM groupe";
                ResultSet result = state.executeQuery(commande);
                
                while(result.next() && !trouve){
                    if(result.getObject(1).toString().equals(groupe))
                        trouve = true;
                }
            }
            return trouve;
        }
        
        public void creationUtilisateur(String nom, String prenom, String username, String mdp, String mail, String categorie) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp); Statement state = conn.createStatement()) {
                String commande = "insert into utilisateur values ('"+username+"', '"+mdp+"','"+categorie+"', NULL, '"+nom+"', '"+prenom+"', '"+mail+"')";
                state.executeUpdate(commande);
            }
        }
}
