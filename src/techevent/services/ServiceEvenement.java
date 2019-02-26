/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import techevent.entities.Evenement;
import techevent.entities.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.utils.connexionbd;
/**
 *
 * @author SIDHOM
 */

public class ServiceEvenement {
    Connection C = connexionbd.getinstance().getConn();
    
    
    public void ajouterevenementdeclub(Evenement e1 ){
        try{
        PreparedStatement req = C.prepareStatement("insert into evenement(NOM,CLUB_ID,LOCALISATION,DATEORGANISATION,DESCRIPTION,SOPNSOR_ID,ETATDEFINANCEMENT,CONFIRMATIONORGANISATION) values (?,?,?,?,?,?,?,?)");
        req.setString(1,e1.getNom());
        req.setInt(2, 1);
        req.setString(3, e1.getLocalisation());
        req.setDate(4, e1.getDateorganisation());
        req.setString(5, e1.getDescription());
        req.setInt(6, 1);
        req.setBoolean(7, e1.isEtatdefinancement());
        req.setBoolean(8, e1.isConfirmationorganisation());
        req.execute(); 
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
        
    
       
        public void modifierEvenement (Evenement e){
        try {
            PreparedStatement pt= C.prepareStatement("update evenement set NOM = ?,LOCALISATION = ?,DATEORGANISATION = ?,DESCRIPTION = ?,CONFIRMATIONORGANISATION= ?,ETATDEFINANCEMENT = ? where id=?");
            pt.setString(1, e.getNom());
            pt.setString(2, e.getLocalisation());
            pt.setDate(3, e.getDateorganisation());
            pt.setString(4, e.getDescription());      
            pt.setInt(5, e.getId());        
            pt.setBoolean(6, e.isConfirmationorganisation());
            pt.setBoolean(7, e.isEtatdefinancement());
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void supprimerEvenement(Evenement e){
     
        try {
            PreparedStatement pt=C.prepareStatement("delete from evenement where ID=?");
            pt.setInt(1, e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     }
    public ResultSet affichertousEvenement(){
            try{
            Statement st = C.createStatement();
            String req = "select * from evenement";
            ResultSet rs = st.executeQuery(req);
            return rs;
            }
            catch(SQLException e){
                System.out.println("erreur");
            }
            return null ;
        }
    public ResultSet afficherevenementparetudiant(Etudiant et){
          try{
            Statement st = C.createStatement();
            String req = "select mesevenemements_id from user_evenement where id="+ et.getId();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
            int i = rs.getInt("USER_ID");
            String str = rs.getString("username");      
           }
            }
            catch(SQLException e){
                System.out.println("erreur");
            }
            return null ;
        }
    public void EvenementSponsorise(int id){
         try {
            PreparedStatement pt= C.prepareStatement("update evenement set ETATDEFINANCEMENT = ? where id=?");
            pt.setBoolean(1, true);
            pt.setInt(2, id);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public ArrayList<Integer> getEvenementIdByClubId(int id) {
        ArrayList<Integer> l=new ArrayList<Integer>();
        try {
            PreparedStatement st = C.prepareStatement("select * from evenement where club_id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                l.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public ArrayList<String> getEvenementNomIdByPresidentId(int id)  {
        ServiceClub sc=new ServiceClub();
        ServiceEvenement se=new ServiceEvenement();
        ServiceUser su=new ServiceUser();
        int club=sc.getIdClubbyPresidentId(id);
        ArrayList<Integer> l=se.getEvenementIdByClubId(club);
        ArrayList<String> l2=new  ArrayList<String>();
        for(int a:l){
            try {
                PreparedStatement st=C.prepareStatement("select * from evenement where id=?");
                st.setInt(1, a);
                ResultSet rs=st.executeQuery();
                rs.beforeFirst();
                if(rs.next()){
                    l2.add(rs.getString(7));
                }   } catch (SQLException ex) {
                Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return l2;
    }

    public int getIdbyNom(String value) {
        try {
            PreparedStatement st = C.prepareStatement("select * from evenement where nom=?");
            st.setString(1, value);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<String> getAllEvenementNonSponsorise() {
        ArrayList<String> l=new ArrayList<String>();
        try {
            PreparedStatement st = C.prepareStatement("select * from evenement where etatdefinancement=?");
            st.setBoolean(1, false);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            while(rs.next()) {
                l.add(rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public String getNombyId (int id){
        try {
            PreparedStatement st = C.prepareStatement("select * from evenement where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString(7);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}