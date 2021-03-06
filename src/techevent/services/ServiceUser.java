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
import java.util.ArrayList;
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
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ResultSet TypeUser(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
    
    public String getNom(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(6);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getPrenom(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(9);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getDateNaissance(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getDate(3).toString();}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getMail(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(4);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getNumeroTelephone(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(7);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getEntreprise(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(20);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getClasse(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(13);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getRole(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(18);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getDomaine(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(16);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public String getDepartement(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getString(11);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "null";
    }
    
    public int getId(String email,String mdp){
          try {
             PreparedStatement st=c.prepareStatement("select * from user where email=? and motpasse=?");
             st.setString(1, email);
             st.setString(2, mdp);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getInt(1);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return 0;
    }


  

    public String getNomById(int id) {
        try {
            PreparedStatement st = c.prepareStatement("select * from user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString(6);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getClubId(String email, String mdp) {
        try {
            PreparedStatement st = c.prepareStatement("select * from user where email=? and motpasse=?");
            st.setString(1, email);
            st.setString(2, mdp);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(17);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<String> getAllSponsorNames() {
        try {
            ArrayList<String> l = new ArrayList<String>();
            PreparedStatement st = c.prepareStatement("select * from user where dtype=?");
            st.setString(1, "Sponsor");
            ResultSet rs = st.executeQuery();
            while(rs.next()) {

                l.add(rs.getString(1) + " / " + rs.getString(6) +" "+rs.getString(9));
                
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getEvenementId(String email, String mdp) {
        int id=this.getId(email, mdp);
        try {
            PreparedStatement st = c.prepareStatement("select * from user_evenement where etudiant_id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getIdbyNom(String nom){
           try {
             PreparedStatement st=c.prepareStatement("select * from user where nom=?");
             st.setString(1, nom);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getInt(1);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return 0;
    }
    
    public String getPrenomById(int id) {
        try {
            PreparedStatement st = c.prepareStatement("select * from user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString(9);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
