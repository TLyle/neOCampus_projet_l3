/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jules
 */
public class Authentification extends javax.swing.JFrame {
    
    String username;
    String password;
    String ip_distance;
    Socket socket;
    
    /**
     * Creates new form Authentification
     * @throws java.io.IOException
     */
    public Authentification() throws IOException {
        initComponents();
        username = "";
        password="";
        ip_distance = "127.0.0.1";
    }
    
    private Dimension tailleEcranAdapté(){
        //get local graphics environment
        GraphicsEnvironment graphicsEnvironment =GraphicsEnvironment.getLocalGraphicsEnvironment();
        //get maximum window bounds
        Rectangle rectangle =graphicsEnvironment.getMaximumWindowBounds();
        //Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension((int)rectangle.getWidth(),(int)rectangle.getHeight());
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

        Conteneur_central = new javax.swing.JPanel();
        Mot_de_passe = new javax.swing.JLabel();
        Bouton_creation_compte = new javax.swing.JButton();
        Bouton_connexion = new javax.swing.JButton();
        Nom_utilisateur = new javax.swing.JLabel();
        Entree_Nom_Utilisateur = new javax.swing.JTextField();
        Bouton_mdp_oublié = new javax.swing.JButton();
        Entree_mdp = new javax.swing.JPasswordField();
        Bouton_local = new javax.swing.JButton();
        Entree_ip = new javax.swing.JTextField();
        Bouton_valider_ip = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Conteneur_central.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Mot_de_passe.setText("Mot de passe");
        Conteneur_central.add(Mot_de_passe, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        Bouton_creation_compte.setText("Créer un compte");
        Bouton_creation_compte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_creation_compteActionPerformed(evt);
            }
        });
        Conteneur_central.add(Bouton_creation_compte, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 120, 40));

        Bouton_connexion.setText("Connexion");
        Bouton_connexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_connexionActionPerformed(evt);
            }
        });
        Conteneur_central.add(Bouton_connexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 120, 40));

        Nom_utilisateur.setText("Nom d'utilisateur");
        Conteneur_central.add(Nom_utilisateur, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        Entree_Nom_Utilisateur.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Entree_Nom_Utilisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Entree_Nom_UtilisateurActionPerformed(evt);
            }
        });
        Conteneur_central.add(Entree_Nom_Utilisateur, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 209, -1));

        Bouton_mdp_oublié.setText("Mot de passe oublié");
        Bouton_mdp_oublié.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_mdp_oubliéActionPerformed(evt);
            }
        });
        Conteneur_central.add(Bouton_mdp_oublié, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 140, -1));

        Entree_mdp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Entree_mdp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Entree_mdpActionPerformed(evt);
            }
        });
        Conteneur_central.add(Entree_mdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 210, -1));

        Bouton_local.setText("Local");
        Bouton_local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_localActionPerformed(evt);
            }
        });
        Conteneur_central.add(Bouton_local, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        Entree_ip.setText("Entrez IP distante");
        Entree_ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Entree_ipActionPerformed(evt);
            }
        });
        Conteneur_central.add(Entree_ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, -1));

        Bouton_valider_ip.setText("Valider");
        Bouton_valider_ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_valider_ipActionPerformed(evt);
            }
        });
        Conteneur_central.add(Bouton_valider_ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .addComponent(Conteneur_central, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(326, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(Conteneur_central, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Entree_Nom_UtilisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Entree_Nom_UtilisateurActionPerformed
        username = Entree_Nom_Utilisateur.getText();
    }//GEN-LAST:event_Entree_Nom_UtilisateurActionPerformed

    private void Bouton_creation_compteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_creation_compteActionPerformed
        new Creation_compte().setVisible(true); // lien vers nouvelle fenetre
        this.dispose();
    }//GEN-LAST:event_Bouton_creation_compteActionPerformed

    private void Bouton_mdp_oubliéActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_mdp_oubliéActionPerformed
        new Mdp_Oublie().setVisible(true); // lien vers nouvelle fenetre
        this.dispose();
    }//GEN-LAST:event_Bouton_mdp_oubliéActionPerformed

    private void Bouton_connexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_connexionActionPerformed
        try {
            this.socket = new Socket(ip_distance, 9633);
        } catch (IOException ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
        }
        username = Entree_Nom_Utilisateur.getText();
        char mdp[] = Entree_mdp.getPassword();
        for (char c : mdp)
            password+=c;
        boolean connection = false;
        Connexion co = new Connexion(socket);
        try {
            connection = co.authen(username, password);
        } catch (IOException ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(connection){
            try {
                new Acceuil_Client(socket).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        }else{
            username = "";
            password = "";
        }
    }//GEN-LAST:event_Bouton_connexionActionPerformed

    private void Entree_mdpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Entree_mdpActionPerformed
        char mdp[] = Entree_mdp.getPassword();
        for (char c : mdp)
            password+=c;
    }//GEN-LAST:event_Entree_mdpActionPerformed

    private void Entree_ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Entree_ipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Entree_ipActionPerformed

    private void Bouton_localActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_localActionPerformed
        ip_distance = "127.0.0.1";
    }//GEN-LAST:event_Bouton_localActionPerformed

    private void Bouton_valider_ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_valider_ipActionPerformed
        ip_distance = Entree_ip.getText();
    }//GEN-LAST:event_Bouton_valider_ipActionPerformed

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
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Authentification().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bouton_connexion;
    private javax.swing.JButton Bouton_creation_compte;
    private javax.swing.JButton Bouton_local;
    private javax.swing.JButton Bouton_mdp_oublié;
    private javax.swing.JButton Bouton_valider_ip;
    private javax.swing.JPanel Conteneur_central;
    private javax.swing.JTextField Entree_Nom_Utilisateur;
    private javax.swing.JTextField Entree_ip;
    private javax.swing.JPasswordField Entree_mdp;
    private javax.swing.JLabel Mot_de_passe;
    private javax.swing.JLabel Nom_utilisateur;
    // End of variables declaration//GEN-END:variables
}
