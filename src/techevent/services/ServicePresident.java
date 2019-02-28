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
import techevent.entities.President;
import techevent.utils.connexionbd;

/**
 *
 * @author Taboubi
 */
public class ServicePresident {
    Connection c=connexionbd.getinstance().getConn();
    
    public void AjouterPresident(President e){
        Statement st;
        try {
            st = c.createStatement();
            String req="insert into user (nom,dtype,prenom,datedenaissance,email,motpasse,numerotelephone,classe,responsabilite,MONCLUB_ID) values('"+e.getNom()+"','etudiant','"+e.getPrenom()+"','"+e.getDatedenaissance()+"','"+e.getEmail()+"','"+e.getMotpasse()+"',"+e.getNumerotelephone()+",'"+e.getClasse()+"','president','"+e.getMonclub().getId()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePresident.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void ModifierPresident(int id,President e){
       
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("update user set nom=? ,prenom=? ,email=? ,numerotelephone=? ,motpasse=? ,classe=?,MONCLUB_ID=?   where id=?");
            pt.setString(1,e.getNom() );
            pt.setString(2, e.getPrenom());
            pt.setString(3, e.getEmail());
            pt.setLong(4, e.getNumerotelephone());
            pt.setString(5, e.getMotpasse());
            pt.setString(6, e.getClasse());
            pt.setInt(7,e.getMonclub().getId());
            pt.setInt(8, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePresident.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public ResultSet AfficherPresident(){
     Statement st;
        try {
            st = c.createStatement();
            String req="select * from user where role='president'";
            ResultSet rs=st.executeQuery(req);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePresident.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }  
     }
     
     public ResultSet afficherInfoPersonne(int e) throws SQLException{
        PreparedStatement ps=c.prepareStatement("select * from user where ID=?");
        ps.setInt(1,e);   
        ResultSet rs= ps.executeQuery();
        return rs;
     }
     
    public void SupprimerPresident(President e){
        try {
            PreparedStatement pt=c.prepareStatement("delete from user where id=?");
            pt.setInt(1,e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePresident.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
