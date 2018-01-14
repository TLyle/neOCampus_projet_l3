/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author LYLE_
 */
public class Ticket {
    private String titre;
    private String groupeEmetteur;
    private String groupeDestinataire;
    private int idTicket;
    private List<Message> message = new LinkedList<>();

    public Ticket(String titre, String groupeEmetteur, String groupeDestinataire, int idTicket) {
        this.titre = titre;
        this.groupeEmetteur = groupeEmetteur;
        this.groupeDestinataire = groupeDestinataire;
        this.idTicket = idTicket;
    }

    public String getGroupeDestinataire() {
        return groupeDestinataire;
    }

    public String getGroupeEmetteur() {
        return groupeEmetteur;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public String getTitre() {
        return titre;
    }

    @Override
    public String toString() {
        return ("Ticket : "+titre);
    }
    
    public List<Message> getMessage() {
        return message;
    }
    
    public void afficherMessage(){
        List<Message> mess = this.getMessage();
        for(int j=0; j < mess.size(); j++){
            System.out.println(mess.get(j).getTexte());
        }
    }
 
    public void addMessage(Message mess){
        if(!message.contains(mess))
            message.add(mess);
    }
 
}
