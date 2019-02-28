package techevent.entities;

import java.util.List;



public class Responsable extends Etudiant 
{ 
	private Club club;
	private String responsabilite;
        
	public Responsable() {
		super();
	}

        public Responsable(int id, String nom, String prenom, String classe, String email, long numerotelephone,String responsabilite) {
            super(id, nom, prenom, classe, email, numerotelephone);
            this.responsabilite = responsabilite;
        }

        public Club getClub() {
            return club;
        }

        public void setClub(Club club) {
            this.club = club;
        }

        public String getResponsabilite() {
            return responsabilite;
        }

         public void setResponsabilite(String responsabilite) {
            this.responsabilite = responsabilite;
        }
}
