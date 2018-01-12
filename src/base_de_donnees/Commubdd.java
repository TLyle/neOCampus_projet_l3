package base_de_donnees;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import objet.Ticket;

import objet.TypeUtilisateur;
import objet.Utilisateur;

public class Commubdd {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/projet";
	private static String ident_user = "root";
	private static String ident_mdp = "";
	
	public boolean verif_user(String user, String mdp) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp);
		Statement state = conn.createStatement();
                String com = "SELECT * FROM utilisateur";
		ResultSet result = state.executeQuery(com);
		Boolean retour = false;
		
		while(result.next() && !retour) {
			String bdd_user = result.getObject(1).toString();
			String bdd_mdp = result.getObject(2).toString();
			if(user.equals(bdd_user) && mdp.equals(bdd_mdp)) {
				retour = true;
			}
		}
		result.close();
		state.close();
		return retour;
	}
	
	public Utilisateur recupUser(String login) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp);
		Statement state = conn.createStatement();
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
		Utilisateur user = null;
		
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
		}
		return user;
	}
	
        /*public int getNbGrp(String categorie) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            int cpt = 0;
            String commande;
            Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp);
            Statement state = conn.createStatement();
            if(categorie.equals("etude"))
                commande = "SELECT * FROM groupe WHERE (Categorie = 'Etude')";
            else
                commande = "SELECT * FROM groupe WHERE (Categorie = 'Technique')";
            ResultSet result = state.executeQuery(commande);
            
            while(result.next())
                cpt++;
            
            return cpt;
        }*/
        
        public List getListGrp(String categorie) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            List<String> liste = new LinkedList<>();
            String commande;
            Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp);
            Statement state = conn.createStatement();
            if(categorie.equals("etude"))
                commande = "SELECT * FROM groupe WHERE (Categorie = 'Etude')";
            else
                commande = "SELECT * FROM groupe WHERE (Categorie = 'Technique')";
            ResultSet result = state.executeQuery(commande);
            
            while(result.next())
                liste.add(result.getObject(1).toString());
            
            return liste;
        }
        
        public Ticket extraireTicket(ResultSet result) throws SQLException{
            Ticket tick;
            System.out.println("Dans la fonction");
            int idTicket = result.getInt(1);
            String titre = result.getObject(2).toString();
            String groupeDestinataire = result.getObject(3).toString();
            String groupeEmetteur = result.getObject(4).toString();
            tick = new Ticket(titre, groupeEmetteur, groupeDestinataire, idTicket);
            
            return tick;
        }
        
        public List getListTicket(String grpEmetteur, String grpDestinataire) throws ClassNotFoundException, SQLException{
            Class.forName(driver);
            List<Ticket> liste = new LinkedList<>();
            Connection conn = DriverManager.getConnection(url, ident_user, ident_mdp);
            Statement state = conn.createStatement();
            
            String commande = "SELECT * FROM ticket WHERE (GroupeSortant = '"+grpEmetteur+"') AND (GroupeDestinataire = '"+grpDestinataire+"')";
            ResultSet result = state.executeQuery(commande);
            
            while(result.next()){
                liste.add(extraireTicket(result));
            }         
            return liste;
        }
}
