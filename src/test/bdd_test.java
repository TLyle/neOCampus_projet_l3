package test;

import base_de_donnees.Commubdd;
import java.sql.SQLException;

import Client.*;
import Serveur.*;
import java.util.List;
import objet.Ticket;
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
                List<Ticket> list = bdd.getListTicket("TDA2","Plombier");
                for(int i =0; i< list.size(); i++){
                    list.get(i).afficherMessage();
                }
                System.out.println(bdd.getListUser());
        }
}