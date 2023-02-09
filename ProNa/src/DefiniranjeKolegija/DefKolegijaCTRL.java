package DefiniranjeKolegija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DefKolegijaCTRL {
	
	private List<KolegijModel> listaKolegija = new ArrayList<KolegijModel>();
	
	public List<KolegijModel> dohvatiKolegije() {
		ucitajIzDatoteke();
		
		return listaKolegija;
			
	}
	
	public void spremi(KolegijModel kolegij) {
		
			
		int index = listaKolegija.indexOf(kolegij);
		
		if (index >= 0) {
			
			listaKolegija.set(index, kolegij);
			spremiUDatoteku();
	
		}
	}
	
	public void obrisi(KolegijModel kolegij) {
		
		
		int index = listaKolegija.indexOf(kolegij);
		
		if (index >= 0) {
			
			listaKolegija.remove(index);
			spremiUDatoteku();
	
		}
	}
	
	public void ubaci(KolegijModel kolegij) {
		
		listaKolegija.add(kolegij);
		spremiUDatoteku();
	}
	
	
	private void spremiUDatoteku(){
		try {
			FileOutputStream upisUDatoteku = new FileOutputStream("kolegiji.dat");
			ObjectOutput upisObjekta = new ObjectOutputStream(upisUDatoteku);
			upisObjekta.writeObject (listaKolegija);
		} catch (IOException e){
			e.printStackTrace();
		}

	}
	
	private void ucitajIzDatoteke() {
		
		try {
			FileInputStream citanjeDatoteke = new FileInputStream("kolegiji.dat");
			if (citanjeDatoteke.available() > 0) {
				ObjectInputStream citajObjekt = new ObjectInputStream (citanjeDatoteke);
				listaKolegija = (List<KolegijModel>) citajObjekt.readObject();
			}
		} catch (ClassNotFoundException e){
			//e.printStackTrace();
		} catch (IOException e){
			//e.printStackTrace();
		}
	}
	

}
