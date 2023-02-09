package DefiniranjeKriterijaOcjena;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DefKritOcjeneCTRL {
	
	private List<KriterijModel> listaKriterija = new ArrayList<KriterijModel>();
	
	public List<KriterijModel> dohvatiKriterijeOcjena() {
		ucitajIzDatoteke();
		
		return listaKriterija;
			
	}
	
	public void spremi(KriterijModel kriterij) {
		
			
		int index = listaKriterija.indexOf(kriterij);
		
		if (index >= 0) {
			
			listaKriterija.set(index, kriterij);
			spremiUDatoteku();
	
		}
	}
	
	public void obrisi(KriterijModel kriterij) {
		
		
		int index = listaKriterija.indexOf(kriterij);
		
		if (index >= 0) {
			
			listaKriterija.remove(index);
			spremiUDatoteku();
	
		}
	}
	
	public void ubaci(KriterijModel kriterij) {
		
		listaKriterija.add(kriterij);
		spremiUDatoteku();
	}
	
	
	private void spremiUDatoteku(){
		try {
			FileOutputStream upisUDatoteku = new FileOutputStream("kriterijiOcjenjivanja.dat");
			ObjectOutput upisObjekta = new ObjectOutputStream(upisUDatoteku);
			upisObjekta.writeObject (listaKriterija);
		} catch (IOException e){
			e.printStackTrace();
		}

	}
	
	private void ucitajIzDatoteke() {
		
		try {
			FileInputStream citanjeDatoteke = new FileInputStream("kriterijiOcjenjivanja.dat");
			if (citanjeDatoteke.available() > 0) {
				ObjectInputStream citajObjekt = new ObjectInputStream (citanjeDatoteke);
				listaKriterija = (List<KriterijModel>) citajObjekt.readObject();
			}
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	

}
