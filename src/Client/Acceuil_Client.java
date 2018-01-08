/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objet.Utilisateur;

/**
 *
 * @author jules
 */
public class Acceuil_Client extends javax.swing.JFrame implements ActionListener {
    static Socket socket;
    Utilisateur moi;
    toServer lui;
    /**
     * Creates new form Acceuil_Client
     */
    ArrayList<Integer> listeDeTickets; //TODO remplacer Integer par Ticket
    
    public Acceuil_Client(Socket s) throws IOException {
        initComponents();
        socket = s;
        lui = new toServer(socket);
        moi = lui.receptionUser();
        Donnees_utilisateur.setText(moi.toString());
    }
    
    private Dimension tailleEcranAdapté(){
        //get local graphics environment
        GraphicsEnvironment graphicsEnvironment =GraphicsEnvironment.getLocalGraphicsEnvironment();
        //get maximum window bounds
        Rectangle rectangle =graphicsEnvironment.getMaximumWindowBounds();
        Dimension dim = new Dimension((int)rectangle.getWidth(),(int)rectangle.getHeight());
        return dim;
    }
    
    private Dimension tailleEcranTicket(){
        //get local graphics environment
        GraphicsEnvironment graphicsEnvironment =GraphicsEnvironment.getLocalGraphicsEnvironment();
        //get maximum window bounds
        Rectangle rectangle =graphicsEnvironment.getMaximumWindowBounds();
        Dimension dim = new Dimension( ((int)rectangle.getWidth()) * 1 / 3,(int)rectangle.getHeight());
        return dim;
    }
    private Dimension tailleEcranProfil(){
        //get local graphics environment
        GraphicsEnvironment graphicsEnvironment =GraphicsEnvironment.getLocalGraphicsEnvironment();
        //get maximum window bounds
        Rectangle rectangle =graphicsEnvironment.getMaximumWindowBounds();
        //Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension( ((int)rectangle.getWidth()) * 2 / 3,(int)rectangle.getHeight());
        return dim;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Ticket = new javax.swing.JPanel();
        Conteneur_boutons_ticket = new javax.swing.JPanel();
        Bouton_recherche = new javax.swing.JButton();
        Bouton_actualiser = new javax.swing.JButton();
        Bouton_creer_ticket = new javax.swing.JButton();
        Conteneur_Tickets = new javax.swing.JPanel();
        jPanel_Profil = new javax.swing.JPanel();
        jPanel_informations = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Donnees_utilisateur = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        Bouton_deconnection = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(tailleEcranAdapté());
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel_Ticket.setBackground(new java.awt.Color(0, 204, 153));
        jPanel_Ticket.setPreferredSize(tailleEcranTicket());

        Conteneur_boutons_ticket.setBackground(new java.awt.Color(0, 153, 153));
        Conteneur_boutons_ticket.setPreferredSize(new java.awt.Dimension(601, 135));
        Conteneur_boutons_ticket.setLayout(new java.awt.GridLayout(1, 0));

        Bouton_recherche.setText("Rechercher");
        Bouton_recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_rechercheActionPerformed(evt);
            }
        });
        Conteneur_boutons_ticket.add(Bouton_recherche);

        Bouton_actualiser.setText("Actualiser");
        Bouton_actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_actualiserActionPerformed(evt);
            }
        });
        Conteneur_boutons_ticket.add(Bouton_actualiser);

        Bouton_creer_ticket.setText("Créer un ticket");
        Bouton_creer_ticket.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Conteneur_boutons_ticket.add(Bouton_creer_ticket);

        Conteneur_Tickets.setLayout(new java.awt.GridLayout(10, 1));

        javax.swing.GroupLayout jPanel_TicketLayout = new javax.swing.GroupLayout(jPanel_Ticket);
        jPanel_Ticket.setLayout(jPanel_TicketLayout);
        jPanel_TicketLayout.setHorizontalGroup(
            jPanel_TicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Conteneur_boutons_ticket, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
            .addComponent(Conteneur_Tickets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_TicketLayout.setVerticalGroup(
            jPanel_TicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TicketLayout.createSequentialGroup()
                .addComponent(Conteneur_boutons_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Conteneur_Tickets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel_Ticket);

        jPanel_Profil.setBackground(new java.awt.Color(0, 102, 204));
        jPanel_Profil.setPreferredSize(tailleEcranProfil());

        Donnees_utilisateur.setColumns(20);
        Donnees_utilisateur.setRows(5);
        jScrollPane2.setViewportView(Donnees_utilisateur);

        javax.swing.GroupLayout jPanel_informationsLayout = new javax.swing.GroupLayout(jPanel_informations);
        jPanel_informations.setLayout(jPanel_informationsLayout);
        jPanel_informationsLayout.setHorizontalGroup(
            jPanel_informationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );
        jPanel_informationsLayout.setVerticalGroup(
            jPanel_informationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );

        jPanel3.setLayout(new java.awt.BorderLayout());

        Bouton_deconnection.setText("Déconnexion");
        Bouton_deconnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_deconnectionActionPerformed(evt);
            }
        });
        jPanel3.add(Bouton_deconnection, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_ProfilLayout = new javax.swing.GroupLayout(jPanel_Profil);
        jPanel_Profil.setLayout(jPanel_ProfilLayout);
        jPanel_ProfilLayout.setHorizontalGroup(
            jPanel_ProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ProfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ProfilLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_informations, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ProfilLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_ProfilLayout.setVerticalGroup(
            jPanel_ProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ProfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jPanel_informations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel_Profil);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Bouton_rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_rechercheActionPerformed
        
    }//GEN-LAST:event_Bouton_rechercheActionPerformed

    private void Bouton_deconnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_deconnectionActionPerformed
        try {
            new Authentification().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_Bouton_deconnectionActionPerformed

    private void Bouton_actualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_actualiserActionPerformed
        //Conteneur_Tickets = new JPanel(new GridLayout(0, 1));
        for(int i = 0; i<10; i++) {
            JPanel p = new JPanel(new FlowLayout());
            JButton b;
            b = new JButton("Acceder a la conversation "+((Integer) i).toString());
            b.addActionListener(this);

            p.add(b);
            p.add(new JLabel("Titre de conversation "+((Integer) i).toString()));

            Conteneur_Tickets.add(p);
        }
        Conteneur_Tickets.updateUI(); // rafraichi le panel
    }//GEN-LAST:event_Bouton_actualiserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Acceuil_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Acceuil_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Acceuil_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Acceuil_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Acceuil_Client(socket).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Acceuil_Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bouton_actualiser;
    private javax.swing.JButton Bouton_creer_ticket;
    private javax.swing.JButton Bouton_deconnection;
    private javax.swing.JButton Bouton_recherche;
    private javax.swing.JPanel Conteneur_Tickets;
    private javax.swing.JPanel Conteneur_boutons_ticket;
    private javax.swing.JTextArea Donnees_utilisateur;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_Profil;
    private javax.swing.JPanel jPanel_Ticket;
    private javax.swing.JPanel jPanel_informations;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getActionCommand());
        new Affichage_Ticket().setVisible(true);
        //this.dispose();
    }

}
