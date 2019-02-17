package techevent.entities;

import java.util.List;

import java.sql.*;



public class Formateur extends User 
{ 
	private String domaine;
	private Blob cv;
        
	public Formateur() {
		super();
	}

	public String getDomaine() {
		return domaine;
	}
        
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public Blob getCv() {
		return cv;
	}
        
	public void setCv(Blob cv) {
		this.cv = cv;
	}
}
