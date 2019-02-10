package techevents.entities;

import java.sql.Date;
import java.util.List;



public class projet
{  
	private int id ;
	private String nom ; 
	private String domaine;
	private String description;
	private boolean etat;
	private club club ;
	private sponsor sponsor;

    public projet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public club getClub() {
        return club;
    }

    public void setClub(club club) {
        this.club = club;
    }

    public sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(sponsor sponsor) {
        this.sponsor = sponsor;
    }
        
}
