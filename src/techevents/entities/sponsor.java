package techevents.entities;

import java.util.List;




public class sponsor extends user 
{ 
	private String nomentreprise;
	private List<evenement> evenementsponsorse;
	public sponsor() {
		super();
	}
	public String getNomentreprise() {
		return nomentreprise;
	}
	public void setNomentreprise(String nomentreprise) {
		this.nomentreprise = nomentreprise;
	}
	public List<evenement> getEvenementsponsorse() {
		return evenementsponsorse;
	}
	public void setEvenementsponsorse(List<evenement> evenementsponsorse) {
		this.evenementsponsorse = evenementsponsorse;
	} 
	

}
