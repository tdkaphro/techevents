package techevent.services;
import java.sql.*;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.entities.Evenement;
import techevent.entities.Offre;
import techevent.entities.Sponsor;
import techevent.utils.connexionbd;
/**
 *
 * @author Taboubi
 */
public class ServiceOffre {
    Connection c = techevent.utils.connexionbd.getinstance().getConn();
    
    public void AjouterOffre(Offre f,int even,int spons){
      Statement st;
        try {
            st = c.createStatement();
            String req = "insert into offre (prix,evenement_id,sponsor_id) values("+f.getPrix()+","+even+","+spons+")";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    public void modifierOffre(int pr,int id,int even,int spons){
         PreparedStatement pt;
        try {
            pt = c.prepareStatement("update offre set prix=? ,evenement_id=? ,sponsor_id=? where id=?");
            pt.setInt(1, pr);
            pt.setInt(2,even);
            pt.setInt(3,spons);
            pt.setInt(4, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void supprimerOffre(int id){
        try {
            PreparedStatement pt = c.prepareStatement("delete from offre where id=?");
            pt.setInt(1, id);
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
                System.out.println("Offre num : " + rs.getInt(1) + " /Prix : " + rs.getInt(2) + " / Evenement num : " + rs.getInt(3) + " / Sponsor num : " + rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getPrixDeOffrebyId(int id){
        
        try {
             PreparedStatement st=c.prepareStatement("select prix from offre where id=?");
             st.setInt(1, id);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getInt(1);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
            return 0;
        }


    public int getEvenIdDeOffrebyId(int id){
        
        try {
             PreparedStatement st=c.prepareStatement("select EVENEMENT_ID  from offre where id=?");
             st.setInt(1, id);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getInt(1);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
            return 0;
        }
    
    public int getSponsIdDeOffrebyId(int id){
        
        try {
             PreparedStatement st=c.prepareStatement("select SPONSOR_ID  from offre where id=?");
             st.setInt(1, id);
             ResultSet rs=st.executeQuery();
             rs.beforeFirst();
             if(rs.next()){return rs.getInt(1);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
            return 0;
        }
    
    public ResultSet getDemandeSponsorisation(String mail,String mdp){
        ServiceUser su=new ServiceUser();
        int id=su.getId(mail, mdp);
        try {
             PreparedStatement st=c.prepareStatement("select * from offre where sponsor_id=?");
             st.setInt(1, id);
             ResultSet rs=st.executeQuery();
             return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
}

