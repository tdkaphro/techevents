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
 * @author ahmed
 */
public class ServiceUser{
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
             if(rs.next()){return rs.getString(8);}
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
             if(rs.next()){return rs.getString(3);}
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
             if(rs.next()){return rs.getString(18);}
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
             if(rs.next()){return rs.getString(12);}
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
             if(rs.next()){return rs.getString(17);}
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
             if(rs.next()){return rs.getString(15);}
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
             if(rs.next()){return rs.getString(10);}
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
}