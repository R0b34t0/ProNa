package DefiniranjeKolegija;

import java.io.Serializable;

public class KolegijModel implements Serializable {
	
	private String naziv;


	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
