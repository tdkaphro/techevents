/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 import techevent.entities.Club;
import techevent.entities.Projet;
import techevent.entities.Sponsor;
import techevent.utils.connexionbd;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hannachi
 */
public class ServiceProjet {
    Connection c = connexionbd.getinstance().getConn();
    public void ajouterprojetpourunclub(int cl, Projet p){
        try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("insert into projet (description,domaine,etat,nom,club_id)values(?,?,?,?,?)");
         st.setString(1,p.getDescription());
         st.setString(2,p.getDomaine());
         st.setBoolean(3, false);
         st.setString(4,p.getNom());
         st.setInt(5,cl);
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
    public void ajouterprojetpourenseignant(int en, Projet p){
         try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("insert into projet (description,domaine,etat,nom,club_id,enseignant_id)values(?,?,?,?,?,?)");
         st.setString(1,p.getDescription());
         st.setString(2,p.getDomaine());
         st.setBoolean(3, false);
         st.setString(4,p.getNom());
         st.setInt(5,en);
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }      
    }    
    
    public void modifierprojet(Projet p){
        try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("update projet set description = ?,set domaine = ?,set nom = ?"); 
         st.setString(1,p.getDescription());
         st.setString(2,p.getDomaine());
         st.setBoolean(3, true);
         st.setString(4,p.getNom());
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
    public void ajoutersponsor(Sponsor sp){
        try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("update projet set sponsor_id = ?, set etat = 1"); 
         st.setInt(1,sp.getId());
         st.setBoolean(2, true);
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
    
    
     public void supprimerprojet(Projet p){
        try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("delete from projet where id=?, "); 
         st.setInt(1, p.getId());
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
     public ResultSet affichertousProjets(Projet p){
            try{
            Statement st = c.createStatement();
            String req = "select * from projet";
            ResultSet rs = st.executeQuery(req);
            return rs;
            }
            catch(SQLException e){
                System.out.println("erreur");
            }
            return null ;
        }
}
