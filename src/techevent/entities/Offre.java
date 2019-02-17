package techevent.entities;

import java.sql.Date;
import java.util.List;

public class Offre
{

	private int id ;
	private int prix ; 
	private Sponsor sponsor;
	private Evenement evenement;

        public Offre(int id, int prix, Sponsor sponsor, Evenement evenement) {
            this.id = id;
            this.prix = prix;
            this.sponsor = sponsor;
            this.evenement = evenement;
        }
        
	public Offre() {
		super();
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
	
	

}
