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
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.entities.Etudiant;
import techevent.entities.Formateur;
import techevent.utils.connexionbd;

/**
 *
 * @author theboy
 */
public class ServiceFormateur {

    Connection c = techevent.utils.connexionbd.getinstance().getConn();

    public void ajouterFormateur(Formateur f) {
        try {
            PreparedStatement req = c.prepareStatement("insert into user(DTYPE,DATEDENAISSANCE,EMAIL,motpasse,NOM,NUMEROTELEPHONE,PRENOM,DOMAINE,picture)values(?,?,?,?,?,?,?,?,?)");
            req.setString(1, "Formateur");
            req.setDate(2, f.getDatedenaissance());
            req.setString(3, f.getEmail());
            req.setString(4, f.getMotpasse());
            req.setString(5, f.getNom());
            req.setLong(6, f.getNumerotelephone());
            req.setString(7, f.getPrenom());
            req.setString(8, f.getDomaine());
           req.setString(9, f.getPicture());

            req.execute();
            ;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void modifierFormateur(int id, Formateur f) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("update user set DATEDENAISSANCE=? ,EMAIL=? ,MOTDEPASSE=? ,NOM=? ,NUMEROTELEPHONE=? ,PRENOM=?,DOMAINE=?,CV=? where id=?");
            pt.setDate(1, f.getDatedenaissance());
            pt.setString(2, f.getEmail());
            pt.setString(3, f.getPrenom());
            pt.setString(4, f.getMotpasse());
            pt.setString(5, f.getNom());
            pt.setLong(6, f.getNumerotelephone());
            pt.setString(7, f.getDomaine());
            pt.setBlob(8, f.getCv());
            pt.setInt(9, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Formateur afficherFormateur(int id) {
        Statement st;
        try {
            st = c.createStatement();
            String req = "select * from user where id = "+id;
            ResultSet rs = st.executeQuery(req);
            rs.next();
            Formateur f = new Formateur();
            f.setNom(rs.getString("nom"));
            f.setPrenom(rs.getString("prenom"));
            f.setEmail(rs.getString("email"));
            f.setNumerotelephone(Integer.parseInt(rs.getString("NUMEROTELEPHONE")));
            f.setPicture(rs.getString("PICTURE"));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void supprimerFormateur(Formateur f) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from user where id=?");
            pt.setInt(1, f.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
