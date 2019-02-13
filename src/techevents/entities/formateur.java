package techevents.entities;

import java.util.List;

import java.sql.*;



public class formateur extends user 
{ 
	private String domaine;
	private List<formation> mesformationscrees;
	private Blob cv;
	public formateur() {
		super();
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public List<formation> getMesformationscrees() {
		return mesformationscrees;
	}
	public void setMesformationscrees(List<formation> mesformationscrees) {
		this.mesformationscrees = mesformationscrees;
	}
	public Blob getCv() {
		return cv;
	}
	public void setCv(Blob cv) {
		this.cv = cv;
	}

}
