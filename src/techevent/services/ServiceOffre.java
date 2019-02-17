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
import techevent.entities.Offre;
import techevent.utils.connexionbd;
/**
 *
 * @author Taboubi
 */
public class ServiceOffre {
    Connection c = techevent.utils.connexionbd.getinstance().getConn();
    public void ajouterOffre(Offre f){
      Statement st;
        try {
            st = c.createStatement();
            String req = "insert into offre (prix,evenement_id,sponsor_id) values("+f.getPrix()+","+f.getEvenement().getId()+","+f.getSponsor().getId()+")";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public void modifierOffre(Offre f,int id){
         PreparedStatement pt;
        try {
            pt = c.prepareStatement("update offre set prix=? ,evenement_id=? ,sponsor_id=? where id=?");
            pt.setInt(1, f.getPrix());
            pt.setInt(2,f.getEvenement().getId());
            pt.setInt(3,f.getSponsor().getId());
            pt.setInt(4, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void supprimerOffre(Offre f){
        try {
            PreparedStatement pt = c.prepareStatement("delete from offre where id=?");
            pt.setInt(1, f.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void afficherOffre(){
        Statement st;
        try {
            st = c.createStatement();
            String req = "select * from offre ";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("offre num : " + rs.getInt(1) + " /prix : " + rs.getInt(2) + " / Evenement num : " + rs.getInt(3) + " / Sponsor num : " + rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
