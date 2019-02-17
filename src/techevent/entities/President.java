package techevent.entities;

import java.util.List;




public class President extends Etudiant 
{  
	private Club club;

	public President() {
		super();
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

}
