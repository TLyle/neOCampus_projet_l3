package objet;

import java.util.LinkedList;
import java.util.List;

public class Utilisateur {
	private String nom, prenom, user_name, mail, groupe;
	private TypeUtilisateur type;
        private List<Ticket> list = new LinkedList<>();
	
	public Utilisateur(String nom, String prenom, String user_name, String mail, String groupe, TypeUtilisateur type) {
		this.nom = nom;
		this.prenom = prenom;
		this.user_name = user_name;
		this.mail = mail;
		this.type = type;
		this.groupe = groupe;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public TypeUtilisateur getType() {
		return type;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public String getGroupe() {
		return groupe;
	}
	
        public void addTicket(Ticket ticket){
            if(list.contains(ticket))
                list.remove(ticket);
            list.add(ticket);
        }
        
	@Override
	public String toString() {
		return("Nom "+nom+"\nPrenom "+prenom+"\nmail "+mail+"\nuser_name "+user_name+"\ngroupe "+groupe+"\ntype "+type);
	}
}
