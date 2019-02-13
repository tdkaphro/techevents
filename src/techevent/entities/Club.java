package techevent.entities;

import java.sql.Date;
import java.util.List;

public class Club implements java.io.Serializable 
{ 
	private int id ;
	private String nom ; 
	private String universiteduclub;
	private Date datedecreation;
	private String domaineduclub;
	private President president;
	private List<Etudiant> invitations;
	private int fraisinscription;
        private boolean confirmationcreation;
        
	public Club() {
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
	public String getUniversiteduclub() {
		return universiteduclub;
	}
	public void setUniversiteduclub(String universiteduclub) {
		this.universiteduclub = universiteduclub;
	}
	public Date getDatedecreation() {
		return datedecreation;
	}
	public void setDatedecreation(Date datedecreation) {
		this.datedecreation = datedecreation;
	}
	public String getDomaineduclub() {
		return domaineduclub;
	}
	public void setDomaineduclub(String domaineduclub) {
		this.domaineduclub = domaineduclub;
	}
	public President getPresident() {
		return president;
	}
	public void setPresident(President president) {
		this.president = president;
	}
	public List<Etudiant> getInvitations() {
		return invitations;
	}
	public void setInvitations(List<Etudiant> invitations) {
		this.invitations = invitations;
	}
	public int getFraisinscription() {
		return fraisinscription;
	}
	public void setFraisinscription(int fraisinscription) {
		this.fraisinscription = fraisinscription;
	}

        public boolean isConfirmationcreation() {
                return confirmationcreation;
        }

        public void setConfirmationcreation(boolean confirmationcreation) {
                this.confirmationcreation = confirmationcreation;
    }
}
