package techevents.entities;

import java.util.List;



public class etudiant extends user 
{
	private String universite;
	private String classe;
	private List<club> clubs;
	private List<formation> mesformations;
	private List<evenement> mesevenement;
	public etudiant() {
		super();
	}
	public String getUniversite() {
		return universite;
	}
	public void setUniversite(String universite) {
		this.universite = universite;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public List<club> getClubs() {
		return clubs;
	}
	public void setClubs(List<club> clubs) {
		this.clubs = clubs;
	}
	public List<formation> getMesformations() {
		return mesformations;
	}
	public void setMesformations(List<formation> mesformations) {
		this.mesformations = mesformations;
	}
	public List<evenement> getMesevenement() {
		return mesevenement;
	}
	public void setMesevenement(List<evenement> mesevenement) {
		this.mesevenement = mesevenement;
	}

}
