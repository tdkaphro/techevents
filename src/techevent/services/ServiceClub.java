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
import techevent.entities.Club;
import techevent.utils.connexionbd;

/**
 *
 * @author Taboubi
 */
public class ServiceClub {
    Connection c=connexionbd.getinstance().getConn();
    
    
    public void AjouterClub(Club cl,int president,int univer){
        Statement st;
        try {
            PreparedStatement ps = c.prepareStatement("insert into club (CONFIRMATIONCREATION,DATEDECREATION,DOMAINEDUCLUB,FRAISINSCRIPTION,NOM,PRESIDENT_ID,UNIVERSITEDUCLUB_ID) values(?,?,?,?,?,?,?)");
            ps.setBoolean(1, cl.isConfirmationcreation());
            ps.setDate(2, cl.getDatedecreation());
            ps.setString(3, cl.getDomaineduclub());
            ps.setInt(4, cl.getFraisinscription());
            ps.setString(5, cl.getNom());
            ps.setInt(6, president);
            ps.setInt(7, univer);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ConfirmerClub(Club cl){
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("update club set confirmationcreation=?  where id=?");
            pt.setBoolean(1, true);
            pt.setInt(2, cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ModifierClub(Club cl,int id){
         PreparedStatement pt;
        try {
            pt = c.prepareStatement("update club set datedecreation=? ,domainedeclub=? ,fraisinscripton=? ,president_id=? ,universitedeclub_id=?,confirmation=?   where id=?");
            pt.setDate(1,cl.getDatedecreation());
            pt.setString(2, cl.getDomaineduclub());
            pt.setInt(3, cl.getFraisinscription());
            pt.setInt(4, cl.getPresident().getId());
            pt.setInt(5, cl.getUniversiteduclub().getId());
            pt.setBoolean(6, cl.isConfirmationcreation());
            pt.setInt(7, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void AfficherClub(){
        
        Statement st;
        try {
            st = c.createStatement();
            String req="select * from club where confirmationcreation='true'";
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                System.out.println("club num : "+rs.getInt(1)+" / nom : "+rs.getString(6)+" / domaine de club : "+rs.getString(4)+" / date de creation : "+rs.getString(3)+" / frais d inscriptions : "+rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AfficherClubEnAttente(){
        
        Statement st;
        try {
            st = c.createStatement();
            String req="select * from club where confirmationcreation='false'";
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                System.out.println("club num : "+rs.getInt(1)+" / nom : "+rs.getString(6)+" / domaine de club : "+rs.getString(4)+" / date de creation : "+rs.getString(3)+" / frais d inscriptions : "+rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SupprimerClub(Club cl){
        try {
            PreparedStatement pt=c.prepareStatement("delete from club where id=?");
            pt.setInt(1,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
