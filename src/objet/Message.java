package objet;

/**
 *
 * @author LYLE_
 */
public class Message {
    String expediteur;
    String Etat;
    String Texte;

    public Message(String expediteur, String Etat, String Texte) {
        this.expediteur = expediteur;
        this.Etat = Etat;
        this.Texte = Texte;
    }

    public String getEtat() {
        return Etat;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public String getTexte() {
        return Texte;
    }
    
    
}
