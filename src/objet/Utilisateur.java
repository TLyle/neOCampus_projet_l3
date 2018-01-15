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

        public List<Ticket> getList() {
            return list;
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
	
	public String getType() {
            switch(this.type){
                case etudiant:
                    return "etudiant";
                case professeur:
                    return "professeur";
                case administrateur:
                    return "administrateur";
                case technique:
                    return "technique";
                default:
                    return "rien";
            }
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
        
        public Ticket hasTicket(int id){
            Ticket tick = null;
            boolean ok = false;
            int i=0;
            while(i<list.size() && !ok){
                if(list.get(i).getIdTicket() == id){
                    ok = true;
                    tick = list.get(i);
                }
                i++;
            }
            return tick;
        }
        
	@Override
	public String toString() {
		return("Nom "+nom+"\nPrenom "+prenom+"\nmail "+mail+"\nuser_name "+user_name+"\ngroupe "+groupe+"\ntype "+type);
	}
        
        public Ticket trouverTicketNom(String nom){
            for(Ticket tick: list){
                if(tick.getTitre().equals(nom))
                    return tick;
            }
            return null;
        }
        
        public void affichageTicket(){
            for(Ticket tick: list){
                System.out.println(tick);
                for(Message mess: tick.getMessage()){
                    System.out.println(mess);
                }
            }
        }
}
