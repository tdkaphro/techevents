package techevents.entities;

import java.sql.Date;
import java.util.List;

public class club implements java.io.Serializable 
{ 
	private int id ;
	private String nom ; 
	private String universitedeclub;
	private Date datedecreation;
	private String domainedeclub;
	private president president;
	private List<responsable> responsables ;
	private List<etudiant> etudiants ;
	private List<formation> formationscrees ;
	private List<evenement> evenementscrees;
	private int fraisinscription;
	public club() {
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
	public String getUniversitedeclub() {
		return universitedeclub;
	}
	public void setUniversitedeclub(String universitedeclub) {
		this.universitedeclub = universitedeclub;
	}
	public Date getDatedecreation() {
		return datedecreation;
	}
	public void setDatedecreation(Date datedecreation) {
		this.datedecreation = datedecreation;
	}
	public String getDomainedeclub() {
		return domainedeclub;
	}
	public void setDomainedeclub(String domainedeclub) {
		this.domainedeclub = domainedeclub;
	}
	public president getPresident() {
		return president;
	}
	public void setPresident(president president) {
		this.president = president;
	}
	public List<responsable> getResponsables() {
		return responsables;
	}
	public void setResponsables(List<responsable> responsables) {
		this.responsables = responsables;
	}
	public List<etudiant> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(List<etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	public List<formation> getFormationscrees() {
		return formationscrees;
	}
	public void setFormationscrees(List<formation> formationscrees) {
		this.formationscrees = formationscrees;
	}
	public List<evenement> getEvenementscrees() {
		return evenementscrees;
	}
	public void setEvenementscrees(List<evenement> evenementscrees) {
		this.evenementscrees = evenementscrees;
	}
	public int getFraisinscription() {
		return fraisinscription;
	}
	public void setFraisinscription(int fraisinscription) {
		this.fraisinscription = fraisinscription;
	}

}
