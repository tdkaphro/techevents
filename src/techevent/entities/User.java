package techevent.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;


public  abstract class User  extends RecursiveTreeObject<User> { 
	
        int id ;
	String nom ;
	String prenom ; 
	private Date datedenaissance;
	private String email;
	private String motpasse;
	private long  numerotelephone;
        String classe;

        private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
        

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
                
	public User() {
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDatedenaissance() {
		return datedenaissance;
	}
	public void setDatedenaissance(Date datedenaissance) {
		this.datedenaissance = datedenaissance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotpasse() {
		return motpasse;
	}
	public void setMotpasse(String motpasse) {
		this.motpasse = motpasse;
	}
	public long getNumerotelephone() {
		return numerotelephone;
	}
	public void setNumerotelephone(long numerotelephone) {
		this.numerotelephone = numerotelephone;
	}
	
}
