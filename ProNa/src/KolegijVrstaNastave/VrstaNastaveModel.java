package KolegijVrstaNastave;

import java.io.Serializable;

public class VrstaNastaveModel implements Serializable {
	
	private String naziv;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


}
