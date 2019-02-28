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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.entities.Club;
import techevent.entities.Etudiant;
import techevent.entities.President;
import techevent.entities.Responsable;
import techevent.utils.connexionbd;

/**
 *
 * @author Taboubi
 */
public class ServiceClub {
    Connection c=connexionbd.getinstance().getConn();
    
    //Club:
    //Demande de création d'un club
    public void AjouterClub(Club cl,int e) throws SQLException {
            ServiceUniverste su=new ServiceUniverste();
            int u=su.etudiantUniversite(e);
            //Créer club
            PreparedStatement req = c.prepareStatement("insert into club(CONFIRMATIONCREATION,DATEDECREATION,DOMAINEDUCLUB,FRAISINSCRIPTION,NOM,UNIVERSITEDUCLUB_ID)values(?,?,?,?,?,?)");
            req.setBoolean(1, false);
            req.setString(2, cl.getDatedecreation());
            req.setString(3, cl.getDomaineduclub());
            req.setDouble(4,cl.getFraisinscription());
            req.setString(5, cl.getNom());
            req.setInt(6, u);
            req.execute(); 
            //Récuperer club_ID
            PreparedStatement req2 = c.prepareStatement("select * from club order by ID desc ");
            ResultSet rs=req2.executeQuery();
            rs.next();
            int clubid=rs.getInt(1);
            // Affecter le club à son président
            PreparedStatement req3 = c.prepareStatement("Update user set MONCLUB_ID=?,DTYPE=? where ID=?"); 
            req3.setInt(1, clubid);
            req3.setString(2, "President");
            req3.setInt(3, e);
            req3.executeUpdate();         
        }
    
    //Recuperer l'id d'un club à partir de son président
    public int getMonClubId(int e) throws SQLException{
        int id=0;
        PreparedStatement pt;
        pt = c.prepareStatement("select MONCLUB_ID from user where ID=? ");
            pt.setInt(1, e); 
            ResultSet rs=pt.executeQuery();
            rs.next();
            id=rs.getInt(1);
            return id;
    } 
    
    //Annuler création club
    public void AnnulerCreation(int e) throws SQLException{
        ServicePresident sp= new ServicePresident();
        ResultSet rs=sp.afficherInfoPersonne(e);
        rs.next();
        PreparedStatement ps=c.prepareStatement("delete from user where ID=?");
        ps.setInt(1, e);
        ps.executeUpdate();
        PreparedStatement ps2=c.prepareStatement("insert into user(ID,DTYPE,DATEDENAISSANCE,EMAIL,MOTPASSE,NOM,NUMEROTELEPHONE,PRENOM,CLASSE,UNIVERSITEETUDIANT_ID)values(?,?,?,?,?,?,?,?,?,?)");
        ps2.setInt(1, rs.getInt(1));
        ps2.setString(2, "Etudiant");
        ps2.setDate(3, rs.getDate(3));
        ps2.setString(4, rs.getString(4));
        ps2.setString(5, rs.getString(5));
        ps2.setString(6, rs.getString(6));
        ps2.setLong(7, rs.getLong(7));
        ps2.setString(8, rs.getString(8));
        ps2.setString(9, rs.getString(9));
        ps2.setInt(10, rs.getInt(13));
        ps2.setInt(11, rs.getInt(14));
        ps2.execute();
    }
    
    public void changerPresident(int e, String email) throws SQLException{
        int cl=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("Update user set DTYPE = ? ,MONCLUB_ID = null where ID = ? ");
        pt.setString(1, "Etudiant");
        pt.setInt(2, e);
        pt.executeUpdate();
        
        PreparedStatement pt2=c.prepareStatement("Update user set DTYPE= ?, MONCLUB_ID=?  where EMAIL=?");
        pt2.setString(1, "President");
        pt2.setInt(2, cl);
        pt2.setString(3, email);
        pt2.executeUpdate();
        
    }
    
    //Récuperer l'état d'un club
    public boolean etatduclub(int e) throws SQLException{
        int clubid=getMonClubId(e);
        boolean etat=false;
        PreparedStatement pt=c.prepareStatement("select * from club where ID=?");
        pt.setInt(1, clubid);
        ResultSet rs=pt.executeQuery();
        rs.next();
        etat=rs.getBoolean(2);
        return etat;
    }
    
    //Modifier un club
    public void ModifierClub(int e, String domaine, String nom ,int frais) throws SQLException{   
    int clubid=getMonClubId(e);
        PreparedStatement req = c.prepareStatement("Update club set DOMAINEDUCLUB = ? ,FRAISINSCRIPTION = ? ,NOM =? where ID=? ");
            req.setString(1,domaine);
            req.setInt(2,frais);
            req.setString(3,nom);   
            req.setInt(4, clubid);
            req.executeUpdate();
    }
    
    // Supprimer un club
    public void SupprimerClub(int e) throws SQLException{
        int clubid=getMonClubId(e);
        annulerResonsables(e);
        AnnulerCreation(e); 
        PreparedStatement pt=c.prepareStatement("delete from club where id=?");
            pt.setInt(1,clubid);
            pt.executeUpdate();
     }
     
    public void ConfirmerClub(Club cl){
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("update club set confirmationcreation=?  where id=?");
            pt.setBoolean(1, true);
            pt.setInt(2, cl.getId());
            pt.executeUpdate();
            PreparedStatement pt2 = c.prepareStatement("update user set Club_Id=?");
            pt2.setInt(1, cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Afficher club à partir son président id
    public ResultSet afficherClubParPresident(int e) throws SQLException {
        int cl=getMonClubId(e);
        PreparedStatement pt;
            pt = c.prepareStatement("select * from club where ID=?");
            pt.setInt(1, cl);
            ResultSet rs=pt.executeQuery();
            rs.next();
            return rs;
    }
    
    public ResultSet afficherPresident(int cl) throws SQLException {
        PreparedStatement pt;
            pt = c.prepareStatement("select * from user where MONCLUB_ID =?");
            pt.setInt(1, cl);
            ResultSet rs=pt.executeQuery();
            rs.next();
            return rs;
    }
    
    //Afficher club à partir son président id
    public ResultSet afficherClub(int cl) throws SQLException {
        PreparedStatement pt;
            pt = c.prepareStatement("select * from club where ID=?");
            pt.setInt(1, cl);
            ResultSet rs=pt.executeQuery();
            rs.next();
            return rs;
    }
    
    // Afficher les club en attente
    public ResultSet AfficherClubEnAttente() throws SQLException{
        Statement st = c.createStatement();
        String req="select * from club where confirmationcreation='false'";
        ResultSet rs=st.executeQuery(req);
        rs.next();
        return rs;    
    }
     
    // Afficher tous les club comfirmés
    public List<Club> trierClub(int e) throws SQLException{
        ServiceUniverste su=new ServiceUniverste();
        int universiteid=su.etudiantUniversite(e);
        List<Club> listclub =new ArrayList<>();
        PreparedStatement pt;
            pt = c.prepareStatement("select * from club where UNIVERSITEDUCLUB_ID=? and CONFIRMATIONCREATION=?");
            pt.setInt(1, universiteid);
            pt.setBoolean(2, true);
            ResultSet rs=pt.executeQuery();
            while(rs.next())
            {
                listclub.add(new Club(rs.getInt(1),rs.getString(6), rs.getString(4), rs.getInt(5)));
            }
            return listclub;
    }
    
    // Afficher les club selon le domaine
    public List<Club> trierClubParDomaine(int e,String domaine) throws SQLException{
        ServiceUniverste su=new ServiceUniverste();
        int universiteid=su.etudiantUniversite(e);
        List<Club> listclub =new ArrayList<>();
        PreparedStatement pt;
            pt = c.prepareStatement("select * from club where UNIVERSITEDUCLUB_ID=? and DOMAINEDUCLUB=? and CONFIRMATIONCREATION=?");
            pt.setInt(1, universiteid);
            pt.setString(2, domaine);
            pt.setBoolean(3, true);
            ResultSet rs=pt.executeQuery();
            while(rs.next())
            {
                listclub.add(new Club (rs.getInt(1),rs.getString(6), rs.getString(4), rs.getInt(5)));
            }
            return listclub;
    }
    
    //Invitation
    public void DemandeDeRejoindre(int e,int cl) throws SQLException{
        PreparedStatement pt=c.prepareStatement("insert into club_user(club_ID,invitations_ID)values(?,?)");
        pt.setInt(1, cl);
        pt.setInt(2, e);
        pt.execute();
    }
    
    public void RefuserInvitation(int m,int e) throws SQLException{
         int cl=getMonClubId(e);
         AnnulerInvitation(m, cl);
    }
    
    public void AnnulerInvitation(int e,int cl) throws SQLException{
         PreparedStatement pt2=c.prepareStatement("delete from club_user where club_ID=? && invitations_ID=?");
         pt2.setInt(1, cl);
         pt2.setInt(2, e);
         pt2.executeUpdate();
    }
    
    //Président
    public int notificationInvitation(int e) throws SQLException{
        int clubid=getMonClubId(e);
        int notif=0;
        PreparedStatement pt=c.prepareStatement("select invitations_ID from club_user where club_ID= ? ");
        pt.setInt(1, clubid);
        ResultSet rs=pt.executeQuery();
        while(rs.next())
        {
          notif++;  
    }
        return notif;
    }
    
    public void AccepterInvitation(int m,int e) throws SQLException{
         int cl=getMonClubId(e);
         PreparedStatement pt=c.prepareStatement("insert into user_club(etudiant_ID,clubs_ID)values(?,?)");
         pt.setInt(1, m);
         pt.setInt(2, cl);
         pt.execute();
         RefuserInvitation(m, e);
    }
    
    public int verifierInvitation(int e,int cl) throws SQLException{
         int ex=0; // Just etudiant
         PreparedStatement pt=c.prepareStatement("select * from club_user where club_Id=?");
         pt.setInt(1, cl);
         ResultSet rs=pt.executeQuery();
         while(rs.next())
         {
             if(rs.getInt(1)==e);
             ex=1; // en attente d'acceptation
         }
         return ex;     
    }
    
    public List<Etudiant> AfficherInvitation(int e) throws SQLException{
       int id=getMonClubId(e);
       PreparedStatement pt=c.prepareStatement("select * from club_user where club_ID =?");
       pt.setInt(1, id);
       ResultSet rs=pt.executeQuery();
       List<Etudiant> list=new ArrayList<>();
       while (rs.next()){
       PreparedStatement pt2=c.prepareStatement("select * from user where ID=?");
       pt2.setInt(1, rs.getInt(2));
       ResultSet rs2= pt2.executeQuery();
       rs2=pt2.executeQuery();
       rs2.next();
       list.add(new Etudiant(rs2.getInt(1),rs2.getString(6),rs2.getString(8),rs2.getString(12),rs2.getString(4),rs2.getLong(7)));
        }
       return list;
    }

    //Membre
    //Verifier si le membre exist ou non
    public boolean verifierMembre(int e,int cl) throws SQLException{
         boolean etat=false; // pas membre
         PreparedStatement pt=c.prepareStatement("select * from user_club where clubs_Id=?");
         pt.setInt(1, cl);
         ResultSet rs=pt.executeQuery();
         while(rs.next())
         {
             if(rs.getInt(1)==e);
             etat=true; // en attente d'acceptation
         }
         return etat;
    }
    
    public void supprimerMembre(int e,int m) throws SQLException{
         int id=getMonClubId(e);
         PreparedStatement pt2=c.prepareStatement("delete from user_club where etudiant_ID =? && clubs_ID=? ");
         pt2.setInt(1, m);
         pt2.setInt(2, id);
         pt2.executeUpdate();
    }
 
    public void quitterclub(int e,int m) throws SQLException{
         PreparedStatement pt2=c.prepareStatement("delete from user_club where etudiant_ID =? && clubs_ID=? ");
         pt2.setInt(1, e);
         pt2.setInt(2, m);
         pt2.executeUpdate();
    }
    
    // Afficher les membres d'un club
    public List<Etudiant> AfficherMembres(int e) throws SQLException{
       int id=getMonClubId(e);
       PreparedStatement pt=c.prepareStatement("select * from user_club where clubs_ID=?");
       pt.setInt(1, id);
       ResultSet rs=pt.executeQuery();
       List<Etudiant> list=new ArrayList<>();
       while (rs.next()){
       PreparedStatement pt2=c.prepareStatement("select * from user where ID=?");
       pt2.setInt(1, rs.getInt(1));
       ResultSet rs2= pt2.executeQuery();
       rs2=pt2.executeQuery();
       rs2.next();
       list.add(new Etudiant(rs2.getInt(1),rs2.getString(6),rs2.getString(8),rs2.getString(12),rs2.getString(4),rs2.getLong(7)));
        }
       return list;
    }
    

    // Afficher le nombre des membres d'un club
    public int NombreDesMembres(int e) throws SQLException {
        int cl=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("select * from user_club where clubs_ID=?");
        pt.setInt(1, cl);
        ResultSet rs=pt.executeQuery();
        int count=0;
        while(rs.next())
        {
            count++;
        }
        return count;
    }
    
     // Afficher les club dont j'appartient
    public List<Club> clubspersonel(int e) throws SQLException{
        List<Club> listclub =new ArrayList<>();
        PreparedStatement pt1=c.prepareStatement("select * from user_club where etudiant_ID=?");
        pt1.setInt(1, e);
        ResultSet rs1= pt1.executeQuery();
        while(rs1.next()){
        PreparedStatement pt2=c.prepareStatement("select * from club where ID= ?");
        pt2.setInt(1, rs1.getInt(2));
        ResultSet rs2=pt2.executeQuery();
        rs2.next();
        listclub.add(new Club (rs2.getInt(1),rs2.getString(6), rs2.getString(4), rs2.getInt(5)));
        }
        return listclub;
    }
    
    public int mesclub(int e) throws SQLException{
        PreparedStatement pt=c.prepareStatement("select * from user_club where etudiant_ID=?");
        int count=0;    
        pt.setInt(1, e);
        ResultSet rs=pt.executeQuery();
        while(rs.next())
        {
            count++;
        }
        return count;
    }
    
    public int NombreDesResponsables(int e) throws SQLException {
        int cl=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("select * from user where CLUB_ID=?");
        pt.setInt(1, cl);
        ResultSet rs=pt.executeQuery();
        int count=0;
        while(rs.next())
        {
            count++;
        }
        return count;
    }    
    
    //Résponsable
    public void affecterResponsabilité(int e,int res, String responsabilité) throws SQLException{
        int clubid=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("update user set DTYPE = ? ,RESPONSABILITE =?,CLUB_ID= ? where ID=? ");
        pt.setString(1, "Responsable");
        pt.setString(2, responsabilité);
        pt.setInt(3, clubid);
        pt.setInt(4, res);
        pt.executeUpdate();
        supprimerMembre(e, res);
    }
    

   public int testerResponsabilite(int e, String res) throws SQLException{
       int cl=getMonClubId(e);
       PreparedStatement pt=c.prepareStatement("select * from user where DTYPE = ? && CLUB_ID = ?");
       pt.setString(1,"Responsable");
       pt.setInt(2, cl);
       ResultSet rs=pt.executeQuery();
       while(rs.next())
       {
           System.out.println(rs.getString("RESPONSABILITE"));
           if(rs.getString("RESPONSABILITE")==res);
           return 1;
       }
       return 0;
   }
  
    // Afficher les membres d'un club
    public List<Responsable> afficherResponsables(int e) throws SQLException{
       int id=getMonClubId(e);
       List<Responsable> list=new ArrayList<>();
       PreparedStatement pt2=c.prepareStatement("select * from user where CLUB_ID=?");
       pt2.setInt(1, id);
       ResultSet rs2= pt2.executeQuery();
       rs2=pt2.executeQuery();
       while(rs2.next()){
       list.add(new Responsable(rs2.getInt(1),rs2.getString(6),rs2.getString(9),rs2.getString(13),rs2.getString(4),rs2.getLong(7),rs2.getString(18)));
        }
       return list;
    }
    
    public void annulerResponsabilite(int e, int m) throws SQLException
    {   
        int id=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("update user set DTYPE =?, CLUB_ID = null, RESPONSABILITE = ? where ID=?");
        pt.setString(1,"Etudiant");
        pt.setString(2, "");
        pt.setInt(3, m);
        pt.executeUpdate();
        PreparedStatement pt2=c.prepareStatement("insert into user_club(etudiant_ID,clubs_ID)values(?,?)");
        pt2.setInt(1, m);
        pt2.setInt(2, id);
        pt2.execute();
    }
    public void annulerResonsables(int e) throws SQLException{
        int id=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("Select * from user where CLUB_ID = ?");
        pt.setInt(1, id);
        ResultSet rs=pt.executeQuery();
        while(rs.next())
        {
            PreparedStatement pt2=c.prepareStatement("update user set DTYPE = ?, CLUB_ID = null, RESPONSABILITE = ? where ID=?");
        pt2.setString(1,"Etudiant");
        pt2.setString(2, "");
        pt2.setInt(3, rs.getInt(1));
        pt2.executeUpdate();
        }
    }
    
    public void supprimerResponsable(int e, int m) throws SQLException
    {   
        int id=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("update user set DTYPE =?, CLUB_ID = null, RESPONSABILITE = ? where ID=?");
        pt.setString(1,"Etudiant");
        pt.setString(2, "");
        pt.setInt(3, m);
        pt.executeUpdate();
    
    }
    
    // Afficher le nombre des formations d'un club
    public int NombreDesFormations(int e) throws SQLException {
        int cl=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("select * from formation where CLUB_ID=?");
        pt.setInt(1,cl);
        ResultSet rs=pt.executeQuery();
        int count=0;
        while(rs.next())
        {
            count++;
        }
        return count;
    }
    
    // Afficher le nombre de projet d'un club
    public int NombreDesProjets(int e) throws SQLException {
        int cl=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("select * from projet where CLUB_ID=?");
        pt.setInt(1,cl);
        ResultSet rs=pt.executeQuery();
        int count=0;
        while(rs.next())
        {
            count++;
        }
        return count;
    }
    
    // Afficher le nombre de projet d'un club
    public int NombreDesEvenements(int e) throws SQLException {
        int cl=getMonClubId(e);
        PreparedStatement pt=c.prepareStatement("select * from evenement where CLUB_ID=?");
        pt.setInt(1,cl);
        ResultSet rs=pt.executeQuery();
        int count=0;
        while(rs.next())
        {
            count++;
        }
        return count;   
    } 
    
    public int getIdClubbyPresidentId(int id){
        try {
            PreparedStatement st = c.prepareStatement("select * from user where id=?");
            st.setInt(1, id);
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
}
 
