package techevent.entities;

import java.util.List;




public class Sponsor extends User 
{ 
	private String nomentreprise;
	
        public Sponsor() {
		super();
	}
	public String getNomentreprise() {
		return nomentreprise;
	}
	public void setNomentreprise(String nomentreprise) {
		this.nomentreprise = nomentreprise;
	}
}
