package test;

import java.sql.SQLException;

import Client.*;
import Serveur.*;
import objet.Utilisateur;

public class bdd_test {
	public static void main(String agrs[]) throws ClassNotFoundException, SQLException {
		String user = "thomas";
		String mdp = "oui";
		Commubdd bdd = new Commubdd();
		if(bdd.verif_user(user, mdp))
			System.out.println("oui");
		else
			System.out.println("non");
		Utilisateur util = bdd.recupUser(user);
		System.out.println(util);
	}
}