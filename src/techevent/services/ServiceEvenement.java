/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.services;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    
    
    public void ajouterevenementdeclub(Evenement e1,int a,int b ){
        try{
        PreparedStatement req = C.prepareStatement("insert into evenement(NOM,CLUB_ID,LOCALISATION,DATEORGANISATION,DESCRIPTION,SPONSOR_ID,PAYANT,PRIX,TYPE,CAPACITER,ETATDESPONSORISATION) values (?,?,?,?,?,?,?,?,?,?,?)");
        req.setString(1,e1.getNom());
        req.setInt(2, a);
        req.setString(3, e1.getLocalisation());
        req.setDate(4, e1.getDateorganisation());
        req.setString(5, e1.getDescription());
        req.setInt(6, b);
        req.setString(7, e1.getEtatdefinancement());
        req.setDouble(8, e1.getPrix());
        req.setString(9, e1.getType());
        req.setInt(10, e1.getCapacite());
        req.setBoolean(11, false);
        req.execute(); 
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
    
    public void ajouterevenementdeclub2(Evenement e1,int a,int b ){
        try{
        PreparedStatement req = C.prepareStatement("insert into evenement(NOM,CLUB_ID,LOCALISATION,DATEORGANISATION,DESCRIPTION,SPONSOR_ID,PAYANT,PRIX,SOUSTYPE,CAPACITER,ETATDESPONSORISATION,TYPE) values (?,?,?,?,?,?,?,?,?,?,?,?)");
        req.setString(1,e1.getNom());
        req.setInt(2, a);
        req.setString(3, e1.getLocalisation());
        req.setDate(4, e1.getDateorganisation());
        req.setString(5, e1.getDescription());
        req.setInt(6, b);
        req.setString(7, e1.getEtatdefinancement());
        req.setDouble(8, e1.getPrix());
        req.setString(9, e1.getSoustypeautre());
        req.setInt(10, e1.getCapacite());
        req.setBoolean(11, false);
        req.setString(12, e1.getSoustypeautre());
        req.execute(); 
        }
        catch(SQLException e){
            System.out.println(e);
        
        }
    }
        
    
       
        public void modifierEvenement (Evenement e,int id){
        try {
            PreparedStatement pt= C.prepareStatement("update evenement set NOM = ?,LOCALISATION = ?,DESCRIPTION = ?,CAPACITER= ?,SOUSTYPE = ?,PRIX = ?,TYPE = ? where id=?");
            pt.setString(1, e.getNom());
            pt.setString(2, e.getLocalisation());
            pt.setString(3, e.getDescription()); 
            pt.setInt(4, e.getCapacite());
            pt.setString(5, e.getSoustypeautre());
            pt.setDouble(6, e.getPrix());
            pt.setString(7, e.getType());
            pt.setInt(8, id);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        //modiifer date
        public void modifierEvenement2 (Evenement e,int a) throws SQLException {
          PreparedStatement pt= C.prepareStatement("update evenement set DATEORGANISATION = ? where id=?");
                      pt.setDate(1, e.getDateorganisation());
                      pt.setInt(2, a);
                      
                      pt.executeUpdate();
                      
                      
        }
        
        public void supprimerEvenement(int id){
     
        try {
            PreparedStatement pt=C.prepareStatement("delete from evenement where ID=?");
            pt.setInt(1, id);
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
    public ResultSet afficherEvenementparClub(int id) throws SQLException{
        Statement st = C.createStatement();
        String req = "select * from evenement where CLUB_ID="+ id;
        ResultSet rs = st.executeQuery(req);
        return rs;       
        
    }
    public String affichersponsorevenement(int id) throws SQLException{
        Statement st = C.createStatement();
        String req = "select nom from  user where id = any(select sponsor_id from evenement where id = "+id+")" ;
        ResultSet rs = st.executeQuery(req);
        rs.next();
        return (rs.getString("nom"));
        
    }
    public ResultSet AfficherparEvenement (int id) throws SQLException{
                Statement st = C.createStatement();
                String req = "select * from evenement where id="+id ;
                ResultSet rs = st.executeQuery(req);
        return rs; 
    }
    public void ajouterEtudiantaEvenement(int et,int evenement) throws SQLException  {
        
     PreparedStatement req2 = C.prepareStatement("insert into user_evenement (etudiant_ID,mesevenement_ID) values(?,?)");
        req2.setInt(1,et);
        req2.setInt(2, evenement);
        req2.execute(); 

    }          
    public ArrayList<Evenement> AffihcerEvenavenir (int id) throws SQLException{
        //String req = ;
        ArrayList<Evenement> l=new ArrayList<Evenement>();
        ArrayList<Integer> l2=this.getAllevenForEtud(id);
        for(int a:l2){
            PreparedStatement req2 = C.prepareStatement("select * from evenement where dateorganisation <=? and id=?");
            req2.setString(1, String.valueOf(LocalDate.now().plusDays(14)));
            req2.setInt(2, a);
            ResultSet rs = req2.executeQuery();
            if(rs.next()){
                Evenement e=new Evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setType(rs.getString("type"));
                e.setDescription(rs.getString("DESCRIPTION"));
                e.setFinance1(rs.getString("PAYANT"));
                e.setPrix1(String.valueOf(rs.getInt("PRIX")));
                e.setCapacite(rs.getInt("CAPACITER"));
                e.setDateorganisation(rs.getDate("DATEORGANISATION"));
                l.add(e);
            }
        }
        return l;
    }
    public ResultSet filtrer(String etatdefinancement, String type) {
        try {
            PreparedStatement req = C.prepareStatement("select * from evenement where PAYANT=? and TYPE =? ");
            req.setString(1, etatdefinancement);
            req.setString(2, type);
            ResultSet rs = req.executeQuery();
            return rs;

        } catch (SQLException e) {
            System.out.println(e);
         
        }
           return null;
    }
  public int nombredeparticipant (int a) throws SQLException {
      int k=0;
        try {
            PreparedStatement req = C.prepareStatement("select * from user_evenement where mesevenement_ID =?");
            req.setInt(1, a);
            ResultSet rs = req.executeQuery();
            while(rs.next()){
                k++;
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return k;
    }  
  
  
    public ResultSet afficherEvenementparEtudiant(int id) throws SQLException{
        PreparedStatement pt=C.prepareStatement("select * from evenement where ID=?");
        pt.setInt(1, id);
        ResultSet rs = pt.executeQuery();       
        return rs;       
       
        
    }
    public boolean verifierparticipant(int e,int ev) throws SQLException{
         boolean ex=false; 
         PreparedStatement pt=C.prepareStatement("select * from user_evenement where mesevenement_ID=?");
         pt.setInt(1, ev);
         ResultSet rs=pt.executeQuery();
         while(rs.next())
         {
             if(rs.getInt(1)==e);
             ex=true; 
         }
         return ex;
         
    }

    public void supprimeEtudiantrEvenement(int e,int ev) throws SQLException {
        PreparedStatement req2 = C.prepareStatement("delete from user_evenement where etudiant_ID = ? && mesevenement_ID = ? ");
        req2.setInt(1,e);
        req2.setInt(2,ev);
        req2.executeUpdate(); 

        
    }
    public void participationdpdf (int id ) throws SQLException, FileNotFoundException, DocumentException, IOException {
        String r= ""; 
        Statement st = C.createStatement();
        String req = "select * from  user where id = any(select etudiant_ID from user_evenement where mesevenement_ID = "+id+")" ;
        ResultSet rs = st.executeQuery(req);
         while (rs.next()) {
             Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("participation1.pdf"));
                    document.open();
                           Image image = Image.getInstance ("e:\\pdf3.jpg"); 
                           document.add(image);

                    document.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD,BaseColor.BLUE)));
                             document.close();
                    String[] parametres = new String[]{
                        "C:\\Program Files (x86)\\Adobe\\Reader 11.0\\Reader\\AcroRd32.exe",
                        "participation1.pdf"
                    };
                    Runtime runtime = Runtime.getRuntime();
                     
                    runtime.exec(parametres);
             
           

        }
         
}
    public ArrayList<User> getAllParticipantbyEvenId(int id) throws SQLException{
        ServiceUser su=new ServiceUser();
        ArrayList<User> l=new ArrayList<User>();
        PreparedStatement pt=C.prepareStatement("select * from user_evenement where mesevenement_ID=?");
         pt.setInt(1, id);
         ResultSet rs=pt.executeQuery();
         rs.beforeFirst();
         while(rs.next())
         {
             l.add(su.getUserbyId(rs.getInt(1)));
         }
        return l;
    }
    public ArrayList<Integer> getAllevenForEtud(int id) throws SQLException{
        ArrayList<Integer> l=new ArrayList<Integer>();
        PreparedStatement pt=C.prepareStatement("select * from user_evenement where etudiant_ID=?");
        pt.setInt(1, id);
        ResultSet rs = pt.executeQuery(); 
        rs.beforeFirst();
        while(rs.next()){
            l.add(rs.getInt(2));
        }
        return l;       
       
        
    }
    


    }
