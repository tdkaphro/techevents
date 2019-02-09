package techevents.entities;

import java.sql.Date;
import java.util.List;

public class offre
{

	private int id ;
	private int prix ; 
	private sponsor sponsor;
	private evenement evenement;
	public offre() {
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
	public sponsor getSponsor() {
		return sponsor;
	}
	public void setSponsor(sponsor sponsor) {
		this.sponsor = sponsor;
	}
	public evenement getEvenement() {
		return evenement;
	}
	public void setEvenement(evenement evenement) {
		this.evenement = evenement;
	}
	
	

}
