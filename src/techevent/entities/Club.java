package techevent.entities;

import java.sql.Date;
import java.util.List;

public class Club implements java.io.Serializable 
{ 
	private int id ;
	private String nom ; 
	private Universite universiteduclub;
	private String datedecreation; // changer dans la base
	private String domaineduclub;
	private List<Etudiant> invitations;
	private double fraisinscription;
        private boolean confirmationcreation;
        
	public Club() {
		super();
	}

        public Club(String nom,String datedecreation, String domaineduclub,double fraisinscription) {
            this.nom = nom;
            this.datedecreation = datedecreation;
            this.domaineduclub = domaineduclub;
            this.fraisinscription = fraisinscription;
        }
    
        public Club(String nom,String domaineduclub,double fraisinscription) {
            this.nom = nom;
            this.domaineduclub = domaineduclub;
            this.fraisinscription = fraisinscription;
        }

         public Club(int id, String nom, String domaineduclub, double fraisinscription) {
            this.id = id;
            this.nom = nom;
            this.domaineduclub = domaineduclub;
            this.fraisinscription = fraisinscription;
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
	public Universite getUniversiteduclub() {
		return universiteduclub;
	}
	public void setUniversiteduclub(Universite universiteduclub) {
		this.universiteduclub = universiteduclub;
	}
	public String getDatedecreation() {
		return datedecreation;
	}
	public void setDatedecreation(String datedecreation) {
		this.datedecreation = datedecreation;
	}
	public String getDomaineduclub() {
		return domaineduclub;
	}
	public void setDomaineduclub(String domaineduclub) {
		this.domaineduclub = domaineduclub;
	}

	public List<Etudiant> getInvitations() {
		return invitations;
	}
	public void setInvitations(List<Etudiant> invitations) {
		this.invitations = invitations;
	}
	public double getFraisinscription() {
		return fraisinscription;
	}
	public void setFraisinscription(double fraisinscription) {
		this.fraisinscription = fraisinscription;
	}

        public boolean isConfirmationcreation() {
                return confirmationcreation;
        }

        public void setConfirmationcreation(boolean confirmationcreation) {
                this.confirmationcreation = confirmationcreation;
    }
}
