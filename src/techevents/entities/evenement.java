package techevents.entities;

import java.sql.Date;
import java.util.List;


public class evenement 
{

	private int id ;
	private String nom ; 
	private String localisation;
	private club club;
	private sponsor sopnsor;
	private Date dateorganisation;
	private String description;
	public evenement() {
		super();
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
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public club getClub() {
		return club;
	}
	public void setClub(club club) {
		this.club = club;
	}
	public sponsor getSopnsor() {
		return sopnsor;
	}
	public void setSopnsor(sponsor sopnsor) {
		this.sopnsor = sopnsor;
	}
	public Date getDateorganisation() {
		return dateorganisation;
	}
	public void setDateorganisation(Date dateorganisation) {
		this.dateorganisation = dateorganisation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
