/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.services;

import techevent.images.ServiceEvenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.entities.Universite;
import techevent.utils.connexionbd;

/**
 *
 * @author Hannachi
 */
public class ServiceUniverste {

    Connection C = connexionbd.getinstance().getConn();

    public void ajouterUniversite(Universite u) {
        try {
            PreparedStatement req = C.prepareStatement("insert into universite (LOCALISATION,NOM)values(?,?)");
            req.setString(2, u.getLocalisation());
            req.setString(3, u.getNom());
            req.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void modifierUniversite(Universite u) {
        try {
            PreparedStatement pt = C.prepareStatement("update evenement set LOCALISATION , NOM = ? where id=?");
            pt.setString(1, u.getLocalisation());
            pt.setString(2, u.getNom());
            pt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet afficherUniversite() {
        try {
            Statement st = C.createStatement();
            String req = "select * from universite";
            ResultSet rs = st.executeQuery(req);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void supprimerUniversite(Universite u) {
        try {
            PreparedStatement pt = C.prepareStatement("delete into universite where id=? ");
            pt.setInt(1, u.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUniverste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int etudiantUniversite(int e) throws SQLException{
        int i=0;
        PreparedStatement pt = C.prepareStatement("select * from user where id=? ");
        pt.setInt(1, e);
        ResultSet rs=pt.executeQuery();
        rs.next();
        i = rs.getInt(14);
        return i;
    }
}
