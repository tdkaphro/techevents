/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Hannachi
 */

public class Projet {
        
        private int id ;
	private String nom ; 
	private String domaine;
	private String description;
	private String media;
	private boolean etat;
	private Club club;
	private Sponsor sponsor;
        private List<Enseignant> createurs;

        public Projet() {
        }

     

         public int getId() {
            return id;
        }
         
        public void setId(int id) {
            this.id = id;
        }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMedia() {
        return media;
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
        
        public Club getClub() {
            return club;
        }
        
        public void setClub(Club club) {
            this.club = club;
        }
        
        public Sponsor getSponsor() {
            return sponsor;
        }
        
        public void setSponsor(Sponsor sponsor) {
            this.sponsor = sponsor;
        }
        
        public List<Enseignant> getCreateurs() {
            return createurs;
        }

        public void setCreateurs(List<Enseignant> createurs) {
            this.createurs = createurs;
        }
}
