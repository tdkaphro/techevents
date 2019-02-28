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
import techevent.entities.User;
import techevent.utils.connexionbd;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author wael
 */
public class ServiceProjet {
    Connection c = connexionbd.getinstance().getConn();
    public void ajoutermembredansprojet(int cl, Projet p, User u){
        try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("insert into projet_user (projet_id,createur_id,membre_id,etat)values(?,?,?,?)");
         st.setInt(1, p.getId());
         st.setInt(2,p.getEnseignant().getId());
         st.setInt(3,u.getId());
         st.setBoolean(4, false);
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
    public void ajouterprojetpourenseignant(int en, Projet p){
         try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("insert into projet (description,domaine,etat,nom,enseignant_id,Media,progress)values(?,?,?,?,?,?,?)");
         st.setString(1,p.getDescription());
         st.setString(2,p.getDomaine());
         st.setBoolean(3,p.isEtat());
         st.setString(4,p.getNom());
         st.setInt(5,en);
         st.setString(6,p.getMedia());
         st.setDouble(7,p.getProgress());
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }      
    }    
    
    public void modifierprojet(Projet p){
        try{
         PreparedStatement st = c.prepareStatement("update projet set description =? , domaine= ?, etat =?, nom = ?,progress=? where ID = ?"); 
         st.setString(1,p.getDescription());
         st.setString(2,p.getDomaine());
         st.setBoolean(3, p.isEtat());
         st.setString(4,p.getNom());
         st.setInt(5, p.getProgress());
         st.setInt(6,p.getId());
         st.executeUpdate();
         
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
        public void accepterinvitation(int a,int b){
        try{
         PreparedStatement st = c.prepareStatement("update projet_user set etat =1  where membre_id = ? and projet_id=?");
         st.setInt(1,a);
         st.setInt(2, b);
         st.executeUpdate();
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
    
    
     public void supprimerprojet(int p){
        try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("delete from projet where id=?"); 
         st.setInt(1, p);
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
     public ResultSet affichertousProjets(){
            try{
            Statement st = c.createStatement();
            String req = "select * from projet";
             ResultSet rs = st.executeQuery(req);
            return rs;
            }
            catch(SQLException e){
                System.out.println(e);
            }
            return null ;
        }
     public ResultSet afficherProjetsparenseignant(int en){
            try{
            Statement st = c.createStatement();
            String req = "select * from projet where enseignant_id="+en;
            
             ResultSet rs = st.executeQuery(req);
            return rs;
            }
            catch(SQLException e){
                System.out.println(e);
            }
            return null ;
        }
     public ResultSet afficherProjetsparenseignant2(int en){
            try{
            Statement st = c.createStatement();
            String req = "select * from projet where enseignant_id="+en;
            
             ResultSet rs = st.executeQuery(req);
            while(rs.next()){
            return rs;
            } 
            }
            catch(SQLException e){
                System.out.println(e);
            }
            return null ;
        }
      public ResultSet afficherProjetparinvit(int en) throws SQLException{
  
         try{
            PreparedStatement req =c.prepareStatement("select * from projet where id = any(select projet_id from projet_user where membre_id=? and etat =0)");
            req.setInt(1,en);
            ResultSet rs = req.executeQuery();
           
            return rs;
             }
            catch(SQLException e){
                System.out.println(e);
            }
    
return null;
}
        
      
      
       
     public int getEnsidByProjetId(int pro){
         try{
            PreparedStatement req =c.prepareStatement("select * from projet where ID =?");
            req.setInt(1,pro);
            ResultSet rs = req.executeQuery();
            rs.next();
            int k = rs.getInt("enseignant_id");
            return k; 
            }
             
  
            catch(SQLException e){
                System.out.println(e);
            }
          return 0;
}
 
      public int getMemidByProjetId(int pro){
         try{
            PreparedStatement req =c.prepareStatement("select count(*) as total from projet_user where membre_id = ? and etat = 0");
            req.setInt(1,pro);
            ResultSet rs = req.executeQuery();
            rs.next();
            return rs.getInt("total");
             }
             
  
            catch(SQLException e){
                System.out.println(e);
            }
          return 0;
}
     
     
     public int nombresprojet(int a) throws SQLException{
         int k=0;
         try{
            PreparedStatement req =c.prepareStatement("select count(*) as total from projet where ID =?");
            req.setInt(1,a);
            ResultSet rs = req.executeQuery();
            if(rs.next())
            k = rs.getInt("total");
            }
             
  
            catch(SQLException e){
                System.out.println(e);
            }
          return k;
}
    
     public ArrayList<String> getAllDomaine(){
         ArrayList<String> l= new ArrayList<String>();
          try{
            PreparedStatement req =c.prepareStatement("select * from projet");
            ResultSet rs = req.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                l.add(rs.getString(3));
            }
            }
            catch(SQLException e){
                System.out.println(e);
            }
         return l;
     }
     
     public int getIdbyMail(String mail){
         try{
            PreparedStatement req =c.prepareStatement("select id from user where email='"+mail+"'");
         
            ResultSet rs = req.executeQuery();
            rs.next();
            return rs.getInt("id");
            }
            catch(SQLException e){
                System.out.println(e);
            }
         return 0;  
     }
     
     public void ajoutermembre(int idp,int ide,int idresp){
         try{
         PreparedStatement st =(PreparedStatement)c.prepareStatement("insert into projet_user (projet_ID,createurs_ID,membre_id,etat)values(?,?,?,?)");
         st.setInt(1, idp);
         st.setInt(2, idresp);
         st.setInt(3, ide);
         st.setBoolean(4, false);
         st.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        
        }     
         
     }
     
     public void supprimermembre(int id){
          try{
         PreparedStatement st = (PreparedStatement) c.prepareStatement("delete from projet_user where membre_id=?"); 
         st.setInt(1, id);
         st.execute();
         
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
         
     }
     
     public int getEnsIdbyProjetId(int id){
         try{
            PreparedStatement req =c.prepareStatement("select * from projet where id=?");
            req.setInt(1, id);
            ResultSet rs = req.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                return rs.getInt(9);
            }
            }
            catch(SQLException e){
                System.out.println(e);
            }
         return 0;
         
     }
     
     public String getTypebyId(int id){
         try{
            PreparedStatement req =c.prepareStatement("select * from user where id=?");
            req.setInt(1, id);
            ResultSet rs = req.executeQuery();
            rs.beforeFirst();
            if(rs.next()){
                return rs.getString(2);
            }
            }
            catch(SQLException e){
                System.out.println(e);
            }
         return "";
     }
     
     public int countmembre(int id){
         int k=0;
         try{
            PreparedStatement req =c.prepareStatement("select count(*) as total from projet_user where createurs_ID =?");
            req.setInt(1,id);
            ResultSet rs = req.executeQuery();
            if(rs.next())
            k = rs.getInt("total");
            
            }
         catch(SQLException e){
                System.out.println(e);
            }
         return k; 
     }
     
     public int countenseign(int id) throws SQLException{
         int k=0;
         ArrayList<Integer> l=new ArrayList<Integer>();
          try{
            PreparedStatement req =c.prepareStatement("select * from projet_user where createurs_ID =?");
            req.setInt(1,id);
            ResultSet rs = req.executeQuery();
            while(rs.next()){
                l.add(rs.getInt(3));
            }
            for(int a:l){
             this.getTypebyId(a).equals("enseignant");
             k++;
          }
          }
         catch(SQLException e){
                System.out.println(e);
            }
         return k;
     }
}
