package techevent.entities;

import java.sql.Date;
import java.util.List;


public class Evenement 
{

	private int id ;
	private String nom ; 
	private String localisation;
	private Club club;
	private Sponsor sopnsor;
	private Date dateorganisation;
	private String description;
        private boolean etatdefinancement;
        private boolean confirmationorganisation;
 
	public Evenement() {
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
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public Sponsor getSopnsor() {
		return sopnsor;
	}
	public void setSopnsor(Sponsor sopnsor) {
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
        
	public boolean isEtatdefinancement() {
                return etatdefinancement;
        }

        public void setEtatdefinancement(boolean etatdefinancement) {
                this.etatdefinancement = etatdefinancement;
        }

        public boolean isConfirmationorganisation() {
                return confirmationorganisation;
        }
        public void setConfirmationorganisation(boolean confirmationorganisation) {
                this.confirmationorganisation = confirmationorganisation;
        }
}
