package Serveur;

import java.sql.*;

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
		ResultSet result = state.executeQuery("SELECT * FROM utilisateur");
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
	
}
