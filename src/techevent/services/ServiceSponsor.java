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
import techevent.utils.connexionbd;
import techevent.entities.Sponsor;

/**
 *
 * @author Hannachi
 */
public class ServiceSponsor {

    Connection c = techevent.utils.connexionbd.getinstance().getConn();

    public void ajouterSponsor(Sponsor s) {
        try {
            PreparedStatement req = c.prepareStatement("insert into user(DTYPE,DATEDENAISSANCE,EMAIL,MOTDEPASSE,NOM,NUMEROTELEPHONE,PRENOM,NOMENTREPRISE)values(?,?,?,?,?,?,?,?)");
            req.setString(1, "Sponsor");
            req.setDate(2, s.getDatedenaissance());
            req.setString(3, s.getEmail());
            req.setString(4, s.getMotpasse());
            req.setString(5, s.getNom());
            req.setLong(6, s.getNumerotelephone());
            req.setString(7, s.getPrenom());
            req.setString(8, s.getNomentreprise());
            req.execute();
            ;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void modifierSponsor(int id, Sponsor s) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("update user set DATEDENAISSANCE=? ,EMAIL=? ,MOTDEPASSE=? ,NOM=? ,NUMEROTELEPHONE=? ,PRENOM=?,NOMENTREPRISE=? where id=?");
            pt.setDate(1, s.getDatedenaissance());
            pt.setString(2, s.getEmail());
            pt.setString(3, s.getPrenom());
            pt.setString(4, s.getMotpasse());
            pt.setString(5, s.getNom());
            pt.setLong(6, s.getNumerotelephone());
            pt.setString(7, s.getNomentreprise());
            pt.setInt(8, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherSponsor() {
        Statement st;
        try {
            st = c.createStatement();
            String req = "select * from user where DTYPE='sponsor'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("sponsor nom : " + rs.getString(6) + " / prenom : " + rs.getString(8) + " / numero de telephone : " + rs.getLong(7) + " / nom d'entreprise : " + rs.getString(18) + " / date de naissance : " + rs.getString(3) + " / email : " + rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerSponsor(Sponsor s) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from user where id=?");
            pt.setInt(1, s.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
