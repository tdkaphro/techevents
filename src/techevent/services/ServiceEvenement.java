/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.services;

import java.sql.*;
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
        req.setInt(2, e1.getClub().getId());
        req.setString(3, e1.getLocalisation());
        req.setDate(4, e1.getDateorganisation());
        req.setString(5, e1.getDescription());
        req.setInt(6, e1.getSopnsor().getId());
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
}