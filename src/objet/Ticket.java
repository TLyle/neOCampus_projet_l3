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

    public void setGroupeEmetteur(String groupeEmetteur) {
        this.groupeEmetteur = groupeEmetteur;
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
    
    
}
