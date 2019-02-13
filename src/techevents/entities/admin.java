package techevents.entities;

import java.util.List;



public class admin extends user 
{ 
	private universite universiteadmin;
	private List <evenement> evenementadmin;
	private List <formation> formationadmin;
	private List <club> clubadmin;
	private List <user> useradmin;
	private List <projet> projetadmin;

    public admin() {
    }

    public universite getUniversiteadmin() {
        return universiteadmin;
    }

    public void setUniversiteadmin(universite universiteadmin) {
        this.universiteadmin = universiteadmin;
    }

    public List<evenement> getEvenementadmin() {
        return evenementadmin;
    }

    public void setEvenementadmin(List<evenement> evenementadmin) {
        this.evenementadmin = evenementadmin;
    }

    public List<formation> getFormationadmin() {
        return formationadmin;
    }

    public void setFormationadmin(List<formation> formationadmin) {
        this.formationadmin = formationadmin;
    }

    public List<club> getClubadmin() {
        return clubadmin;
    }

    public void setClubadmin(List<club> clubadmin) {
        this.clubadmin = clubadmin;
    }

    public List<user> getUseradmin() {
        return useradmin;
    }

    public void setUseradmin(List<user> useradmin) {
        this.useradmin = useradmin;
    }

    public List<projet> getProjetadmin() {
        return projetadmin;
    }

    public void setProjetadmin(List<projet> projetadmin) {
        this.projetadmin = projetadmin;
    }
	

}
