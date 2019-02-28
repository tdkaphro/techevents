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
import techevent.entities.Formateur;
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

   public Etudiant afficherEtudiant(int id) {
        Statement st;
        try {
            st = c.createStatement();
            String req = "select * from user where id = "+id;
            ResultSet rs = st.executeQuery(req);
            rs.next();
            Etudiant f = new Etudiant();
            f.setNom(rs.getString("nom"));
            f.setPrenom(rs.getString("prenom"));
            f.setEmail(rs.getString("email"));
            f.setNumerotelephone(Integer.parseInt(rs.getString("NUMEROTELEPHONE")));
            f.setPicture(rs.getString("PICTURE"));
            f.setDatedenaissance(rs.getDate("DATEDENAISSANCE"));
            f.setClasse(rs.getString("classe"));
            return f;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    
    public boolean testerEmail(int e, String s) throws SQLException{
        boolean exist=false;
        ServiceUniverste su= new ServiceUniverste();
        int u=su.etudiantUniversite(e);
        PreparedStatement pt = c.prepareStatement("select EMAIL from user where UNIVERSITEETUDIANT_ID = ? ");
        pt.setInt(1, u);
        ResultSet rs=pt.executeQuery();
        while(rs.next())
        {
            if(rs.getString(1).equals(s))
            {
            return exist=true;
            }
        }
        return exist;
    }
}
