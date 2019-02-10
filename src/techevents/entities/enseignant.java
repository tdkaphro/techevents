package techevents.entities;

import java.util.List;




public class enseignant extends user 
{ 
	private String universiteenseignant;
	private String departement;
	private List<projet> projetenseignant;

    public enseignant() {
    }

    public String getUniversiteenseignant() {
        return universiteenseignant;
    }

    public void setUniversiteenseignant(String universiteenseignant) {
        this.universiteenseignant = universiteenseignant;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public List<projet> getProjetenseignant() {
        return projetenseignant;
    }

    public void setProjetenseignant(List<projet> projetenseignant) {
        this.projetenseignant = projetenseignant;
    }
        
}
