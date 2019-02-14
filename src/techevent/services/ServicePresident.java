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
            String req="insert into user (nom,dtype,prenom,datedenaissance,email,motpasse,numerotelephone,classe,responsabilite,club_id) values('"+e.getNom()+"','etudiant','"+e.getPrenom()+"','"+e.getDatedenaissance()+"','"+e.getEmail()+"','"+e.getMotpasse()+"',"+e.getNumerotelephone()+",'"+e.getClasse()+"','president','"+e.getClub().getId()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePresident.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void ModifierPresident(int id,President e){
       
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("update user set nom=? ,prenom=? ,email=? ,numerotelephone=? ,motpasse=? ,classe=?,club_id=?   where id=?");
            pt.setString(1,e.getNom() );
            pt.setString(2, e.getPrenom());
            pt.setString(3, e.getEmail());
            pt.setLong(4, e.getNumerotelephone());
            pt.setString(5, e.getMotpasse());
            pt.setString(6, e.getClasse());
            pt.setInt(7,e.getClub().getId());
            pt.setInt(8, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePresident.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void AfficherPresident(){
      Statement st;
        try {
            st = c.createStatement();
            String req="select * from user where role='president'";
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                System.out.println("president num : "+rs.getInt(1)+" / nom : "+rs.getString(6)+" / prenom : "+rs.getString(8)+" / date de naissance : "+rs.getString(3)+" / email : "+rs.getString(4)+" / classe : "+rs.getString(12)+" / Club : "+rs.getString(13));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePresident.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
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
    
}
