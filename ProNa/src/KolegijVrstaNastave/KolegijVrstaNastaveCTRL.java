package KolegijVrstaNastave;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class KolegijVrstaNastaveCTRL {
	
	private List<KolegijVrstaNastaveModel> listaKolegijaVrstaNastave = new ArrayList<KolegijVrstaNastaveModel>();
	
	private List<VrstaNastaveModel> listaVrsteNastave = new ArrayList<VrstaNastaveModel>();
	
	public List<KolegijVrstaNastaveModel> dohvatiKolegijeVrstuNastave() {
		ucitajIzDatoteke();
		
		return listaKolegijaVrstaNastave;
			
	}
	
	
	
	public void spremi(KolegijVrstaNastaveModel kolegijVrstaNastave) {
		
			
		int index = listaKolegijaVrstaNastave.indexOf(kolegijVrstaNastave);
		
		if (index >= 0) {
			
			listaKolegijaVrstaNastave.set(index, kolegijVrstaNastave);
			spremiUDatoteku();
	
		}
	}
	
	public void obrisi(KolegijVrstaNastaveModel kolegij) {
		
		
		int index = listaKolegijaVrstaNastave.indexOf(kolegij);
		
		if (index >= 0) {
			
			listaKolegijaVrstaNastave.remove(index);
			spremiUDatoteku();
	
		}
	}
	
	public void ubaci(KolegijVrstaNastaveModel kolegijVrstaNastaveModel) {
		
		listaKolegijaVrstaNastave.add(kolegijVrstaNastaveModel);
		spremiUDatoteku();
	}
	
	
	private void spremiUDatoteku(){
		try {
			FileOutputStream upisUDatoteku = new FileOutputStream("kolegijVrstaNastave.dat");
			ObjectOutput upisObjekta = new ObjectOutputStream(upisUDatoteku);
			upisObjekta.writeObject (listaKolegijaVrstaNastave);
		} catch (IOException e){
			e.printStackTrace();
		}

	}
	
	private void ucitajIzDatoteke() {
		
		try {
			FileInputStream citanjeDatoteke = new FileInputStream("kolegijVrstaNastave.dat");
			if (citanjeDatoteke.available() > 0) {
				ObjectInputStream citajObjekt = new ObjectInputStream (citanjeDatoteke);
				listaKolegijaVrstaNastave = (List<KolegijVrstaNastaveModel>) citajObjekt.readObject();
			}
		} catch (ClassNotFoundException e){
			//e.printStackTrace();
		} catch (IOException e){
			//e.printStackTrace();
		}
	}



	public List<VrstaNastaveModel> getListaVrsteNastave() {
		return listaVrsteNastave;
	}

	public void setListaVrsteNastave() {

		VrstaNastaveModel vrstaNastave1 = new VrstaNastaveModel();
		vrstaNastave1.setNaziv("Predavanje");
		this.listaVrsteNastave.add(vrstaNastave1);
		VrstaNastaveModel vrstaNastave2 = new VrstaNastaveModel();
		vrstaNastave2.setNaziv("Vježbe");
		this.listaVrsteNastave.add(vrstaNastave2);
		VrstaNastaveModel vrstaNastave3 = new VrstaNastaveModel();
		vrstaNastave3.setNaziv("Seminar");
		this.listaVrsteNastave.add(vrstaNastave3);
		VrstaNastaveModel vrstaNastave4 = new VrstaNastaveModel();
		vrstaNastave4.setNaziv("Praktièna nastava");
		this.listaVrsteNastave.add(vrstaNastave4);
	}
	

}
