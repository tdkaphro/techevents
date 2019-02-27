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
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.entities.User;
import techevent.utils.connexionbd;

/**
 *
 * @author Hannachi
 */
public class ServiceUser {
    Connection c=connexionbd.getinstance().getConn();

    public boolean Login(String email, String mdp) {

        try {
            PreparedStatement pt = c.prepareStatement("select * from user where email=? and motpasse=?");
            pt.setString(1, email);
            pt.setString(2, mdp);
            ResultSet rs = pt.executeQuery();
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public User getUserbyId(int id) throws SQLException{
        User u=new User() {
};      PreparedStatement pt=c.prepareStatement("select * from user where ID=?");
         pt.setInt(1, id);
         ResultSet rs=pt.executeQuery();
         if(rs.next()){
             u.setNom(rs.getString(6));
             u.setPrenom(rs.getString(9));
             u.setEmail(rs.getString(4));
             u.setNumerotelephone(rs.getLong(7));
             u.setClasse(rs.getString(13));
         }
        return u;
    }
}
