package techevent.services;

import techevent.images.ServiceEvenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import techevent.entities.Offre;
import techevent.utils.connexionbd;
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

    Connection c = connexionbd.getinstance().getConn();

    public ServiceOffre() {
    }

    public void DemanderOffre(Offre f, int even, int spons) {
        try {
            PreparedStatement pt = c.prepareStatement("insert into offre (prix,evenement_id,sponsor_id,etat,type) values(?,?,?,?,?)");
            pt.setInt(1, f.getPrix());
            pt.setInt(2, even);
            pt.setInt(3, spons);
            pt.setString(4, "En Attente");
            pt.setString(5, "Demande");
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ModifierOffreSpons(int id, int prix, int even) {
        try {
            PreparedStatement st2 = c.prepareStatement("update offre set prix=? , evenement_id=? where id=?");
            st2.setInt(1, prix);
            st2.setInt(2, even);
            st2.setInt(3, id);
            st2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ModifierOffreEven(int id, int prix, int even, int spons) {
        try {
            PreparedStatement st2 = c.prepareStatement("update offre set prix=? , evenement_id=? ,sponsor_id=? where id=?");
            st2.setInt(1, prix);
            st2.setInt(2, even);
            st2.setInt(3, spons);
            st2.setInt(4, id);
            st2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void OffrirOffre(Offre f, int even, int spons) {
        try {
            PreparedStatement pt = c.prepareStatement("insert into offre (prix,evenement_id,sponsor_id,etat,type) values(?,?,?,?,?)");
            pt.setInt(1, f.getPrix());
            pt.setInt(2, even);
            pt.setInt(3, spons);
            pt.setString(4, "En Attente");
            pt.setString(5, "Offre");
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AccepterOffre(int id) {

        try {
            PreparedStatement st = c.prepareStatement("update evenement set etatdesponsorisation=? where id=?");
            st.setBoolean(1, true);
            st.setInt(2, this.getEvenIdDeOffrebyId(id));
            st.executeUpdate();
            PreparedStatement st2 = c.prepareStatement("update offre set etat=? where id=?");
            st2.setString(1, "Valide");
            st2.setInt(2, id);
            st2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RefuserOffre(int id) {
        ServiceEvenement se = new ServiceEvenement();
        try {
            PreparedStatement st = c.prepareStatement("update evenement set etatdesponsorisation=? where id=?");
            st.setBoolean(1, false);
            st.setInt(2, this.getEvenIdDeOffrebyId(id));
            st.executeUpdate();
            PreparedStatement st2 = c.prepareStatement("update offre set etat=? where id=?");
            st2.setString(1, "Refuse");
            st2.setInt(2, id);
            st2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerOffre(int id) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from offre where id=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPrixDeOffrebyId(int id) {
        try {
            PreparedStatement st = c.prepareStatement("select * from offre where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getEvenIdDeOffrebyId(int id) {
        try {
            PreparedStatement st = c.prepareStatement("select * from offre where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getSponsIdDeOffrebyId(int id) {
        try {
            PreparedStatement st = c.prepareStatement("select * from offre where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(6);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public ArrayList<Offre> findAllDemandeforSponseur(int id) {
        ArrayList<Offre> l = new ArrayList<Offre>();
        ServiceEvenement se = new ServiceEvenement();
        ServiceUser su = new ServiceUser();
        try {
            PreparedStatement st = c.prepareStatement("select * from offre where sponsor_id=? and type=? ");
            st.setInt(1, id);
            st.setString(2, "Demande");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Offre f = new Offre();
                f.setId(rs.getInt(1));
                f.setPrix(rs.getInt(3));
                f.setIdeven(rs.getInt(5));
                f.setIdspon(rs.getInt(6));
                f.setNomeven(se.getNombyId(rs.getInt(5)));
                f.setNomspons(su.getNomById(rs.getInt(6)));
                f.setEtat(rs.getString(2));
                l.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;

    }

    public ArrayList<Offre> findAllOffreforSponseur(int id) {
        ArrayList<Offre> l = new ArrayList<Offre>();
        ServiceEvenement se = new ServiceEvenement();
        ServiceUser su = new ServiceUser();
        try {
            PreparedStatement st = c.prepareStatement("select * from offre where sponsor_id=? and type=? ");
            st.setInt(1, id);
            st.setString(2, "Offre");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Offre f = new Offre();
                f.setId(rs.getInt(1));
                f.setPrix(rs.getInt(3));
                f.setIdeven(rs.getInt(5));
                f.setIdspon(rs.getInt(6));
                f.setNomeven(se.getNombyId(rs.getInt(5)));
                f.setNomspons(su.getNomById(rs.getInt(6)));
                f.setEtat(rs.getString(2));
                l.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;

    }

    public ArrayList<Offre> findAllOffreforEvenement(ArrayList<Integer> l) {
        ArrayList<Offre> l2 = new ArrayList<Offre>();
        ServiceEvenement se = new ServiceEvenement();
        ServiceUser su = new ServiceUser();
        for (int a : l) {
            try {
                PreparedStatement st = c.prepareStatement("select * from offre where evenement_id=? and type=? ");
                st.setInt(1, a);
                st.setString(2, "Offre");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Offre f = new Offre();
                    f.setId(rs.getInt(1));
                    f.setPrix(rs.getInt(3));
                    f.setIdeven(rs.getInt(5));
                    f.setIdspon(rs.getInt(6));
                    f.setNomeven(se.getNombyId(rs.getInt(5)));
                    f.setNomspons(su.getNomById(rs.getInt(6)));
                    f.setEtat(rs.getString(2));
                    l2.add(f);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l2;
    }

    public ArrayList<Offre> findAllDemandeforEvenement(ArrayList<Integer> l) {
        ArrayList<Offre> l2 = new ArrayList<Offre>();
        ServiceEvenement se = new ServiceEvenement();
        ServiceUser su = new ServiceUser();
        for (int a : l) {
            try {
                PreparedStatement st = c.prepareStatement("select * from offre where evenement_id=? and type=? ");
                st.setInt(1, a);
                st.setString(2, "Demande");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Offre f = new Offre();
                    f.setId(rs.getInt(1));
                    f.setPrix(rs.getInt(3));
                    f.setIdeven(rs.getInt(5));
                    f.setIdspon(rs.getInt(6));
                    f.setNomeven(se.getNombyId(rs.getInt(5)));
                    f.setNomspons(su.getNomById(rs.getInt(6)));
                    f.setEtat(rs.getString(2));
                    l2.add(f);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l2;
    }

    public ArrayList<String> findAllEvenementforEvenement(ArrayList<Integer> l) {
        ArrayList<String> l2 = new ArrayList<String>();
        ServiceEvenement se = new ServiceEvenement();
        ServiceUser su = new ServiceUser();
        for (int a : l) {
            try {
                PreparedStatement st = c.prepareStatement("select * from evenement where id=?  ");
                st.setInt(1, a);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    l2.add(rs.getString(8));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l2;
    }

    public ArrayList<String> getAllEvenementforSpons(int id) {
        ServiceEvenement se = new ServiceEvenement();
        ArrayList<String> l = new ArrayList<String>();
        try {
            PreparedStatement st = c.prepareStatement("select * from offre where sponsor_id=? and etat=? ");
            st.setInt(1, id);
            st.setString(2, "Valide");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                l.add(se.getNombyId(rs.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public int getPrixbyNomeven(String s) {
        ServiceEvenement se = new ServiceEvenement();
        try {
            PreparedStatement st = c.prepareStatement("select * from offre where evenement_id=?  ");
            st.setInt(1, se.getIdbyNom(s));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<String> findAllEvenementforEven(ArrayList<Integer> l) {
        ArrayList<String> l2 = new ArrayList<String>();
        ServiceEvenement se = new ServiceEvenement();
        ServiceUser su = new ServiceUser();
        for (int a : l) {
            try {
                PreparedStatement st = c.prepareStatement("select * from offre where evenement_id=? and etat=?  ");
                st.setInt(1, a);
                st.setString(2, "Valide");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    l2.add(se.getNombyId(rs.getInt(5)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l2;
    }

    public void AjouterOffre(Offre f, int even, int spons) {
        Statement st;
        try {
            st = c.createStatement();
            String req = "insert into offre (prix,evenement_id,sponsor_id) values(" + f.getPrix() + "," + even + "," + spons + ")";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierOffre(int pr, int id, int even, int spons) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("update offre set prix=? ,evenement_id=? ,sponsor_id=? where id=?");
            pt.setInt(1, pr);
            pt.setInt(2, even);
            pt.setInt(3, spons);
            pt.setInt(4, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherOffre() {
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

    public ResultSet getDemandeSponsorisation(String mail, String mdp) {
        ServiceUser su = new ServiceUser();
        int id = su.getId(mail, mdp);
        try {
            PreparedStatement st = c.prepareStatement("select * from offre where sponsor_id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
