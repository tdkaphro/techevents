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
       // private String etatdesponsorisation;
        private boolean confirmationorganisation;
        private String type;
        private String soustypeautre;
        private double Lon;
        private double Lat;
        private int capacite;
        private double prix ;
        private String etatdefinancement;
        private String prix1;
        private String capacite1;
        private String finance1;
        public int nbrparticipant;

    public int getNbrparticipant() {
        return nbrparticipant;
    }

    public void setNbrparticipant(int nbrparticipant) {
        this.nbrparticipant = nbrparticipant;
    }

    public String getCapacite1() {
        return capacite1;
    }

    public void setCapacite1(String capacite1) {
        this.capacite1 = capacite1;
    }

    public String getFinance1() {
        return finance1;
    }

    public void setFinance1(String finance1) {
        this.finance1 = finance1;
    }
        

    public String getPrix1() {
        return prix1;
    }

    public void setPrix1(String prix1) {
        this.prix1 = prix1;
    }

    public String getEtatdefinancement() {
        return etatdefinancement;
    }

    public void setEtatdefinancement(String etatdefinancement) {
        this.etatdefinancement = etatdefinancement;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

   /* public String getEtatdesponsorisation() {
        return etatdesponsorisation;
    }

    public void setEtatdesponsorisation(String etatdesponsorisation) {
        this.etatdesponsorisation = etatdesponsorisation;
    }*/

  

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
        
        

    public double getLon() {
        return Lon;
    }

    public void setLon(double Lon) {
        this.Lon = Lon;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }


    public String getSoustypeautre() {
        return soustypeautre;
    }

    public void setSoustypeautre(String soustypeautre) {
        this.soustypeautre = soustypeautre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
 
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
        
	

        public boolean isConfirmationorganisation() {
                return confirmationorganisation;
        }
        public void setConfirmationorganisation(boolean confirmationorganisation) {
                this.confirmationorganisation = confirmationorganisation;
        }
}
