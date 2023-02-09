package DefiniranjeKriterijaOcjena;

import java.io.Serializable;

public class KriterijModel implements Serializable {
	
	private String naziv;
	private int ID;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
