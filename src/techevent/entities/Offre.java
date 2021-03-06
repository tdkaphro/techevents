package techevent.entities;

import java.sql.Date;
import java.util.List;

public class Offre
{
    private int id;
    private int prix;
    private Sponsor sponsor;
    private Evenement evenement;
    private int idspon;
    private int ideven;
    private String nomeven;
    private String nomspons;
    String etat;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public int getIdspon() {
        return idspon;
    }

    public void setIdspon(int idspon) {
        this.idspon = idspon;
    }

    public int getIdeven() {
        return ideven;
    }

    public void setIdeven(int ideven) {
        this.ideven = ideven;
    }

    public String getNomeven() {
        return nomeven;
    }

    public void setNomeven(String nomeven) {
        this.nomeven = nomeven;
    }

    public String getNomspons() {
        return nomspons;
    }

    public void setNomspons(String nomspons) {
        this.nomspons = nomspons;
    }
    
    
}
