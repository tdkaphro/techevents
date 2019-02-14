/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.services;

import java.sql.*;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.entities.Etudiant;
import techevent.utils.connexionbd;

/**
 *
 * @author Taboubi
 */
public class ServiceEtudiant {
   Connection c = techevent.utils.connexionbd.getinstance().getConn();

    public void AjouterEtudiant(Etudiant e) {
        Statement st;
        try {
            st = c.createStatement();
            String req = "insert into user (nom,dtype,prenom,datedenaissance,email,motpasse,numerotelephone,classe,responsabilite) values('" + e.getNom() + "','Etudiant','" + e.getPrenom() + "','" + e.getDatedenaissance() + "','" + e.getEmail() + "','" + e.getMotpasse() + "'," + e.getNumerotelephone() + ",'" + e.getClasse() + "','Membre')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ModifierEtudiant(int id, Etudiant e) {

        PreparedStatement pt;
        try {
            pt = c.prepareStatement("update user set nom=? ,prenom=? ,email=? ,numerotelephone=? ,motpasse=? ,classe=?   where id=?");
            pt.setString(1, e.getNom());
            pt.setString(2, e.getPrenom());
            pt.setString(3, e.getEmail());
            pt.setLong(4, e.getNumerotelephone());
            pt.setString(5, e.getMotpasse());
            pt.setString(6, e.getClasse());
            pt.setInt(7, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AfficherEtudiant() {
        Statement st;
        try {
            st = c.createStatement();
            String req = "select * from user where dtype='etudiant'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("etudiant num : " + rs.getInt(1) + " /responsabilite : " + rs.getString(17) + " / nom : " + rs.getString(6) + " / prenom : " + rs.getString(8) + " / date de naissance : " + rs.getString(3) + " / email : " + rs.getString(4) + " / classe : " + rs.getString(12));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SupprimerEtudiant(Etudiant e) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from user where id=?");
            pt.setInt(1, e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
