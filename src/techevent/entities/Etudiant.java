package techevent.entities;

import java.util.List;



public class Etudiant extends User 
{
	private Universite universite;
	private String classe;
	private List<Club> clubs;
	private List<Formation> mesformations;
	private List<Evenement> mesevenement;
        
	public Etudiant() {

	}
        public Etudiant(int id,String nom,String prenom,String classe,String email,long numerotelephone){
            super(id, nom, prenom, email, numerotelephone);
            this.classe=classe;
        }
	public Universite getUniversite() {
		return universite;
	}
	public void setUniversite(Universite universite) {
		this.universite = universite;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public List<Club> getClubs() {
		return clubs;
	}
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	public List<Formation> getMesformations() {
		return mesformations;
	}
	public void setMesformations(List<Formation> mesformations) {
		this.mesformations = mesformations;
	}
	public List<Evenement> getMesevenement() {
		return mesevenement;
	}
	public void setMesevenement(List<Evenement> mesevenement) {
		this.mesevenement = mesevenement;
	}



}
