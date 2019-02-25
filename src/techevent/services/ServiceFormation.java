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

    public void ajouterformationdeclub(Formation p, int cl, int formateur) {
        try {

            PreparedStatement req = C.prepareStatement("insert into formation (CAPACITE,CERTIFICATION,DATEDEBUT,DATEDEFIN,DESCRIPTION,DOMAINE,LOCALISATION,NOM,PRIX,VOLUMEHORAIRE,CLUB_ID,FORMATEUR_ID,lat,lon,confirme)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            req.setInt(1, p.getCapacite());
            req.setBoolean(2, p.isCertification());
            req.setDate(3, p.getDatedebut());
            req.setDate(4, p.getDatedefin());
            req.setString(5, p.getDescription());
            req.setString(6, p.getDomaine());
            req.setString(7, p.getLocalisation());
            req.setString(8, p.getNom());
            req.setDouble(9, p.getPrix());
            req.setInt(10, p.getVolumehoraire());
            req.setInt(11, cl);
            req.setInt(12, formateur);
            req.setDouble(13, p.getLat());
            req.setDouble(14, p.getLon());
            req.setBoolean(15, false);
            req.execute();
            ;
        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public void confirmer(int p) {
        try {
            PreparedStatement req = C.prepareStatement("update formation set confirme=? where id=?");
            req.setInt(1, 1);
            req.setInt(2, p);
            req.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierformation(Formation p) {
        try {
            PreparedStatement req = C.prepareStatement("update formation set CAPACITE = ?,CERTIFICATION = ?,DATEDEBUT = ?,DATEDEFIN= ?,VOLUMEHORAIRE = ?,FORMATEUR_ID= ? where id=?");
            req.setInt(1, p.getCapacite());
            req.setBoolean(2, p.isCertification());
            req.setDate(3, p.getDatedebut());
            req.setDate(4, p.getDatedefin());
            req.setInt(5, p.getVolumehoraire());
            req.setObject(6, p.getFormateur());
            req.setObject(7, p.getId());
            req.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerFormation(Formation e) {

        try {
            PreparedStatement pt = C.prepareStatement("delete from formation where ID=?");
            pt.setInt(1, e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet affichertousFormation() {
        try {
            Statement st = C.createStatement();
            String req = "select * from formation where datedebut>CURRENT_DATE ";
            ResultSet rs = st.executeQuery(req);
            return rs;
        } catch (SQLException e) {
            System.out.println("erreur");
        }
        return null;
    }

    public ResultSet afficherformationparetudiant(int a) {
        try {
            Statement st = C.createStatement();
            String req = "select *  from formation where  id =  any(SELECT mesformations_ID FROM user_formation WHERE etudiant_id = " + a;
            ResultSet rs = st.executeQuery(req);
            return rs;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ResultSet formateurpardomaine(String a) {
        try {
            PreparedStatement req = C.prepareStatement("select * from user where domaine=?");
            req.setString(1, a);
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ResultSet filtrer(String et, String date, String domaine) {
        try {
            PreparedStatement req = C.prepareStatement("select * from formation where domaine=? and ?>=datedebut and id=any(select mesformations_id from user_formation where etudiant_ID=?)");
            req.setString(1, domaine);
            req.setString(2, date);
            req.setString(3, et);
            ResultSet rs = req.executeQuery();
            return rs;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    public ResultSet filtrer1(String date, String domaine) throws SQLException {
        if(domaine!=null){
        try {
          
            PreparedStatement req = C.prepareStatement("select * from formation where domaine=? and ?>=datedebut and CURDATE()<datedebut");
            req.setString(1, domaine);
            req.setString(2, date);

            ResultSet rs = req.executeQuery();
            return rs;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        }else{
              try {
          
            PreparedStatement req = C.prepareStatement("select * from formation where domaine is not null and ?>=datedebut and CURDATE()<datedebut");
            req.setString(1, date);

            ResultSet rs = req.executeQuery();
            return rs;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        }
    }

    public ResultSet filtrer2(String date, int a) {
        try {
            PreparedStatement req = C.prepareStatement("select * from formation where ?>=datedebut and CURDATE()<datedebut and formateur_id=? and confirme=? ");
            req.setString(1, date);
            req.setInt(2, a);
            req.setInt(3, 0);
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ResultSet filtrer3(String date, int a) {
        try {
            PreparedStatement req = C.prepareStatement("select * from formation where ?>=datedebut and CURDATE()<datedebut and formateur_id=? and confirme=? ");
            req.setString(1, date);
            req.setInt(2, a);
            req.setInt(3, 1);
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean ajouterformationaunetudiant(int et, int formation) {

        try {
            Statement st = C.createStatement();
            String req = "select capacite from formation where id = " + formation;
            ResultSet rs = st.executeQuery(req);
            rs.next();
            int h = rs.getInt(1);
            String req1 = "select count(*) as total from user_formation where mesformations_ID = " + formation;
            ResultSet rs1 = st.executeQuery(req1);
            rs1.next();
            int nb = rs1.getInt("total");
            if (h > nb) {
                PreparedStatement req2 = C.prepareStatement("insert into user_formation (etudiant_id,mesformations_id)values(?,?)");
                req2.setInt(1, et);
                req2.setInt(2, formation);
                req2.execute();
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
        return false;
    }

    public ResultSet formationparclub(int a) {
        try {
            Statement st = C.createStatement();
            PreparedStatement req = C.prepareStatement("select * from formation where club_id=?");
            req.setInt(1, a);
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String formateurdeformation(int a) {
        try {
            Statement st = C.createStatement();
            PreparedStatement req = C.prepareStatement("select nom from user where id = any (select formateur_id from formation where formateur_id=?)");
            req.setInt(1, a);
            ResultSet rs = req.executeQuery();
            String h = null;
            while (rs.next()) {
                h = rs.getString("nom");
            }
            return h;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int nombredinscri(int a) throws SQLException {
        try {
            PreparedStatement req = C.prepareStatement("select count(*) as total from user_formation where mesformations_ID =?");
            req.setInt(1, a);
            ResultSet rs = req.executeQuery();
            rs.next();
            int k = rs.getInt("total");
            return k;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    public int nombredeformation(int a) throws SQLException {
        try {
            PreparedStatement req = C.prepareStatement("select count(*) as total from formation where formateur_id =? and confirme=0");
            req.setInt(1, a);
            ResultSet rs = req.executeQuery();
            rs.next();
            int k = rs.getInt("total");
            return k;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public ResultSet afficherformation(int a) throws SQLException {
        try {
            PreparedStatement req = C.prepareStatement("select * from formation where id =?");
            req.setInt(1, a);
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean inscri(int a, int formation) throws SQLException {
        try {
            PreparedStatement req = C.prepareStatement("select * from user_formation where etudiant_id =? and 	mesformations_ID= ? ");
            req.setInt(1, a);
            req.setInt(2, formation);
            ResultSet rs = req.executeQuery();
            if (!rs.next()) {

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    }

    public void desincrire(int a, int formation) throws SQLException {
        try {
            PreparedStatement req = C.prepareStatement("delete  from user_formation where etudiant_id =? and 	mesformations_ID= ? ");
            req.setInt(1, a);
            req.setInt(2, formation);
            req.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
