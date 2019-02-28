package techevent.entities;

import java.util.List;




public class President extends Etudiant 
{  
	private Club monclub;

	public President() {
		super();
	}

	public Club getMonclub() {
		return monclub;
	}

	public void setMonclub(Club monclub) {
		this.monclub = monclub;
	}

}
