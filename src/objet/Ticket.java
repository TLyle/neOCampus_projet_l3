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
    private String groupe;
    private int idTicket;
    private List<String> message = new LinkedList<>();

    public Ticket(String titre, String groupe, int idTicket, String message) {
        this.titre = titre;
        this.groupe = groupe;
        this.idTicket = idTicket;
        this.message.add(message);
    }

    public String getGroupe() {
        return groupe;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public String getTitre() {
        return titre;
    }

    public String toString() {
        return ("Titre "+titre+"\n Groupe "+groupe+"\n Idticket "+idTicket);
    }

    
}
