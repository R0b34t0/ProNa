package KolegijVrstaNastave;

import java.io.Serializable;

import DefiniranjeKolegija.KolegijModel;

public class KolegijVrstaNastaveModel implements Serializable {
	
	private KolegijModel kolegij;
	private VrstaNastaveModel vrstaNastave;


	public KolegijModel getKolegij() {
		return kolegij;
	}

	public void setKolegij(KolegijModel kolegij) {
		this.kolegij = kolegij;
	}

	public VrstaNastaveModel getVrstaNastave() {
		return vrstaNastave;
	}

	public void setVrstaNastave(VrstaNastaveModel vrstaNastave) {
		this.vrstaNastave = vrstaNastave;
	}

}
