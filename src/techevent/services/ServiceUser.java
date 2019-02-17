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
    
    public String TypeUser(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(2);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
}
