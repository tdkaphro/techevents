/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.entities.Enseignant;
import techevent.entities.Sponsor;

/**
 *
 * @author Hannachi
 */
public class ServiceEnseignant {
 Connection c = techevent.utils.connexionbd.getinstance().getConn();
    
    public void ajouterEnseignant(Enseignant e,int univer) {
        try {
        PreparedStatement req = c.prepareStatement("insert into user(DTYPE,DATEDENAISSANCE,EMAIL,MOTDEPASSE,NOM,NUMEROTELEPHONE,PRENOM,DEPARTEMENT,UNIVERSITEENSEIGNANT)values(?,?,?,?,?,?,?,?,?)");
        req.setString(1,"Enseignant");
        req.setDate(2,e.getDatedenaissance());
        req.setString(3,e.getEmail());
        req.setString(4,e.getMotpasse());
        req.setString(5,e.getNom());
        req.setLong(6,e.getNumerotelephone());
        req.setString(7,e.getPrenom());
        req.setString(8,e.getDepartement());
        req.setInt(9,univer);
        req.execute(); 
     ;
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void modifierEnseignant(int id, Enseignant e) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("update user set DATEDENAISSANCE=? ,EMAIL=? ,MOTDEPASSE=? ,NOM=? ,NUMEROTELEPHONE=? ,PRENOM=?,DEPARTEMENT=?,UNIVERSITEENSEIGNANT where id=?");
            pt.setDate(1, e.getDatedenaissance());
            pt.setString(2, e.getEmail());
            pt.setString(3, e.getPrenom());
            pt.setString(4, e.getMotpasse() );
            pt.setString(5, e.getNom());
            pt.setLong(6,e.getNumerotelephone());
            pt.setString(7, e.getDepartement());
            pt.setObject(8, e.getUniversiteenseignant());
            pt.setInt(9, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherEnseignant() {
        Statement st;
        try {
            st = c.createStatement();
            String req = "select * from user where DTYPE='sponsor'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("sponsor nom : " + rs.getString(6) + " / prenom : " + rs.getString(8) + " / numero de telephone : " + rs.getLong(7) + " / universite : " + rs.getObject(11) + " / departement : " + rs.getString(10) + " / date de naissance : " + rs.getString(3) + " / email : " + rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerEnseignant(Enseignant e) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from user where id=?");
            pt.setInt(1, e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
