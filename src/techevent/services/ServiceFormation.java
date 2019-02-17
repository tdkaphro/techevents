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


public class ServiceFormation {
    Connection C = connexionbd.getinstance().getConn();
    
    
    public void ajouterformationdeclub(Formation p , int cl,int formateur,int univer){
        try{
    
    PreparedStatement req = C.prepareStatement("insert into formation (CAPACITE,CERTIFICATION,DATEDEBUT,DATEDEFIN,DESCRIPTION,DOMAINE,LOCALISATION,NOM,PRIX,VOLUMEHORAIRE,CLUB_ID,FORMATEUR_ID,UNIVERSITEDUFORMATION_ID)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        req.setInt(1,p.getCapacite());
        req.setBoolean(2,p.isCertification());
        req.setDate(3,p.getDatedebut());
        req.setDate(4,p.getDatedefin());
        req.setString(5,p.getDescription());
        req.setString(6,p.getDomaine());
        req.setString(7,p.getLocalisation());
        req.setString(8,p.getNom());
        req.setDouble(9,p.getPrix());
        req.setInt(10,p.getVolumehoraire());
        req.setInt(11,cl);
        req.setInt(12,formateur);
        req.execute(); 
     ;
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
        
    
       
        public void modifierformation (Formation p){
        try {
            PreparedStatement req= C.prepareStatement("update formation set CAPACITE = ?,CERTIFICATION = ?,DATEDEBUT = ?,DATEDEFIN= ?,VOLUMEHORAIRE = ?,FORMATEUR_ID= ? where id=?");
             req.setInt(1,p.getCapacite());
        req.setBoolean(2,p.isCertification());
        req.setDate(3,p.getDatedebut());
        req.setDate(4,p.getDatedefin()); 
        req.setInt(5,p.getVolumehoraire());
        req.setObject(6,p.getFormateur());
        req.setObject(7,p.getId());
        req.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void supprimerFormation(Formation e){
     
        try {
            PreparedStatement pt=C.prepareStatement("delete from formation where ID=?");
            pt.setInt(1, e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     }
    public ResultSet affichertousFormation(){
            try{
            Statement st = C.createStatement();
            String req = "select * from formation";
            ResultSet rs = st.executeQuery(req);
            return rs;
            }
            catch(SQLException e){
                System.out.println("erreur");
            }
            return null ;
        }
        public ResultSet afficherformationparetudiant(int a){
          try{
            Statement st = C.createStatement();
            String req = "select *  from formation where  id =  any(SELECT mesformations_ID FROM user_formation WHERE etudiant_id = "+a;
            ResultSet rs = st.executeQuery(req);
               return rs;

              }
            
            catch(SQLException e){
                System.out.println(e);
            }
             return null;
        }
         public void ajouterformationaunetudiant(Etudiant et ,  int formation ){
        try{
        Statement st = C.createStatement();
       String req = "select capaciter from formation where id = "+formation;
       ResultSet rs = st.executeQuery(req);
          Statement st1 = C.createStatement();
       String req1 = "select count(*) from user_formation where mesformations_ID = "+formation;
       ResultSet rs1 = st.executeQuery(req1);
       int nb = rs1.getInt("count");
       if(rs.getInt(3)>nb){
     PreparedStatement req2 = C.prepareStatement("insert into user_formation (etudiant_id,mesformations_id)values(?,?)");
        req2.setInt(1,et.getId());
        req2.setInt(2, formation);
        req2.execute();
       }
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
        
}