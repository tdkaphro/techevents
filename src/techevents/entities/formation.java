package techevents.entities;

import java.sql.Date;
import java.util.List;


public class formation 
{ 

	private int id ;
	private String nom ; 
	private String club;
	private String domaine;
	private String description;
	private Date datedebut;
	private Date datedefin;
	private int volumehoraire;
	private String localisation;
	private int capacite;
	private double prix;
	private boolean certification;
	public formation() {
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
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
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
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	public Date getDatedefin() {
		return datedefin;
	}
	public void setDatedefin(Date datedefin) {
		this.datedefin = datedefin;
	}
	public int getVolumehoraire() {
		return volumehoraire;
	}
	public void setVolumehoraire(int volumehoraire) {
		this.volumehoraire = volumehoraire;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public boolean isCertification() {
		return certification;
	}
	public void setCertification(boolean certification) {
		this.certification = certification;
	}
	

}
