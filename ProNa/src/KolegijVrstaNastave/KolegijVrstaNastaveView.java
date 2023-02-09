package KolegijVrstaNastave;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import DefiniranjeKolegija.DefKolegijaCTRL;
import DefiniranjeKolegija.KolegijModel;
import DefiniranjeKriterijaOcjena.KriterijModel;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class KolegijVrstaNastaveView extends Dialog {

	protected Object result;
	protected Shell shlDefiniranjeKolegija;
	
	private KolegijVrstaNastaveModel korisnickiUnos = new KolegijVrstaNastaveModel();
	private KolegijVrstaNastaveModel izabraniKolegijVrstaNastave = new KolegijVrstaNastaveModel();
	private KolegijVrstaNastaveCTRL defKolegijVrstaNastaveCTRL = new KolegijVrstaNastaveCTRL();
	private DefKolegijaCTRL defKolegijaCTRL = new DefKolegijaCTRL();
	private List<KolegijVrstaNastaveModel> listaKolegijVrstaNastave;
	private List<KolegijModel> listaKolegija;
	private List<VrstaNastaveModel> listaVrsteNastave;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KolegijVrstaNastaveView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDefiniranjeKolegija.open();
		shlDefiniranjeKolegija.layout();
		Display display = getParent().getDisplay();
		while (!shlDefiniranjeKolegija.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlDefiniranjeKolegija = new Shell(getParent(), getStyle());
		shlDefiniranjeKolegija.setSize(379, 300);
		shlDefiniranjeKolegija.setText("Definiranje kolegija");
		
		Button btnSpremi = new Button(shlDefiniranjeKolegija, SWT.NONE);
		Button btnObrisi = new Button(shlDefiniranjeKolegija, SWT.NONE);
		Button btnNovi = new Button(shlDefiniranjeKolegija, SWT.NONE);
		btnObrisi.setEnabled(false);
		
		btnObrisi.setBounds(284, 134, 75, 25);
		btnObrisi.setText("Obri\u0161i");
		
		Combo cmbListaKolegijaVrstaNastave = new Combo(shlDefiniranjeKolegija, SWT.READ_ONLY);
		Combo cmbVrstaNastave = new Combo(shlDefiniranjeKolegija, SWT.READ_ONLY);
		cmbVrstaNastave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				korisnickiUnos.setVrstaNastave(listaVrsteNastave.get(cmbVrstaNastave.getSelectionIndex()));
				if (korisnickiUnos.getKolegij()!=null) {
					btnNovi.setEnabled(true);
				}
			}
		});
		Combo cmbListaKolegija = new Combo(shlDefiniranjeKolegija, SWT.READ_ONLY);
		cmbListaKolegija.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
							
				korisnickiUnos.setKolegij(listaKolegija.get(cmbListaKolegija.getSelectionIndex()));
			
				if (korisnickiUnos.getVrstaNastave()!=null) {
					btnNovi.setEnabled(true);
				}
				
			}
		});
		
		
		cmbListaKolegijaVrstaNastave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				izabraniKolegijVrstaNastave = listaKolegijVrstaNastave.get(cmbListaKolegijaVrstaNastave.getSelectionIndex());
	
				korisnickiUnos.setKolegij(izabraniKolegijVrstaNastave.getKolegij());
				korisnickiUnos.setVrstaNastave(izabraniKolegijVrstaNastave.getVrstaNastave());
				
				cmbListaKolegija.setText(izabraniKolegijVrstaNastave.getKolegij().getNaziv());
				cmbListaKolegija.select(listaKolegija.indexOf(izabraniKolegijVrstaNastave.getKolegij()));
				cmbVrstaNastave.setText(izabraniKolegijVrstaNastave.getVrstaNastave().getNaziv());
				cmbVrstaNastave.select(listaVrsteNastave.indexOf(izabraniKolegijVrstaNastave.getVrstaNastave()));

				btnObrisi.setEnabled(true);
				btnSpremi.setEnabled(true);
				btnNovi.setEnabled(true);
				
			}
		});
		cmbListaKolegijaVrstaNastave.setBounds(10, 37, 352, 23);
		
		
		cmbVrstaNastave.setBounds(198, 96, 164, 23);
		
		
		cmbListaKolegija.setBounds(10, 96, 156, 23);
		
		
		listaKolegijVrstaNastave = defKolegijVrstaNastaveCTRL.dohvatiKolegijeVrstuNastave();
		
		for (int index = 0; index < listaKolegijVrstaNastave.size(); index++) {
			cmbListaKolegijaVrstaNastave.add(listaKolegijVrstaNastave.get(index).getKolegij().getNaziv() + " / " +
											 listaKolegijVrstaNastave.get(index).getVrstaNastave().getNaziv(), index);	
		}
		
		
		listaKolegija = defKolegijaCTRL.dohvatiKolegije();
		
		for (int index = 0; index < listaKolegija.size(); index++) {
			cmbListaKolegija.add(listaKolegija.get(index).getNaziv(), index);	
		}
		
		defKolegijVrstaNastaveCTRL.setListaVrsteNastave ();
		listaVrsteNastave = defKolegijVrstaNastaveCTRL.getListaVrsteNastave();
		for (int index = 0; index < listaVrsteNastave.size(); index++) {
			cmbVrstaNastave.add(listaVrsteNastave.get(index).getNaziv(), index);	
		}
		
		btnObrisi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox messageBox = new MessageBox(shlDefiniranjeKolegija, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
		        
		        messageBox.setText("Brisanje");
		        messageBox.setMessage("Želite li obrisati izabrani kolegij i vrstu nastave?");
		        int buttonID = messageBox.open();
		        switch(buttonID) {
		          case SWT.YES:
		        	  	defKolegijVrstaNastaveCTRL.obrisi(izabraniKolegijVrstaNastave);
						
						//cmbListaKolegijaVrstaNastave.setText("");
						cmbListaKolegijaVrstaNastave.remove(cmbListaKolegijaVrstaNastave.getSelectionIndex());
						cmbListaKolegija.setText("");
						cmbVrstaNastave.setText("");
						
						btnSpremi.setEnabled(false);
						//txtNazivKolegija.setText("");
		          case SWT.NO:
		            // exits here ...
		            break;
		          case SWT.CANCEL:
		            // does nothing ...
		        }
		        
				
				
				
			}
		});
		
		
		
		
		Label lblKolegiji = new Label(shlDefiniranjeKolegija, SWT.NONE);
		lblKolegiji.setBounds(10, 16, 126, 15);
		lblKolegiji.setText("Kolegij i vrsta nastave:");
		
		Label lblNazivKolegija = new Label(shlDefiniranjeKolegija, SWT.NONE);
		lblNazivKolegija.setBounds(10, 75, 91, 15);
		lblNazivKolegija.setText("Naziv kolegija:");
		
		
		btnSpremi.setEnabled(false);
		btnSpremi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//korisnickiUnos = listaKolegijVrstaNastave.get(cmbListaKolegijaVrstaNastave.getSelectionIndex());
				
			
				if (provjeraKolegijaVrsteNastave()){
					
					izabraniKolegijVrstaNastave.setKolegij(korisnickiUnos.getKolegij());
					izabraniKolegijVrstaNastave.setVrstaNastave(korisnickiUnos.getVrstaNastave());
					defKolegijVrstaNastaveCTRL.spremi(izabraniKolegijVrstaNastave);
					
					
					cmbListaKolegijaVrstaNastave.setItem(cmbListaKolegijaVrstaNastave.getSelectionIndex(), 
														 izabraniKolegijVrstaNastave.getKolegij().getNaziv() + "/" + 
														 izabraniKolegijVrstaNastave.getVrstaNastave().getNaziv());
				
				}
			
			}
		});
		btnSpremi.setBounds(10, 134, 75, 25);
		btnSpremi.setText("Spremi");
		
		
		btnNovi.setEnabled(false);
		btnNovi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				korisnickiUnos = new KolegijVrstaNastaveModel();
		
				korisnickiUnos.setKolegij(listaKolegija.get(cmbListaKolegija.getSelectionIndex()));
				korisnickiUnos.setVrstaNastave(listaVrsteNastave.get(cmbVrstaNastave.getSelectionIndex()));


				
				if (provjeraKolegijaVrsteNastave()){
					defKolegijVrstaNastaveCTRL.ubaci(korisnickiUnos);
					listaKolegijVrstaNastave = defKolegijVrstaNastaveCTRL.dohvatiKolegijeVrstuNastave();
					
					
					cmbListaKolegijaVrstaNastave.setText(korisnickiUnos.getKolegij().getNaziv() + "/" + 
														 korisnickiUnos.getVrstaNastave().getNaziv());
					cmbListaKolegijaVrstaNastave.add(korisnickiUnos.getKolegij().getNaziv() + "/" + 
							 						 korisnickiUnos.getVrstaNastave().getNaziv());
					cmbListaKolegijaVrstaNastave.select(cmbListaKolegijaVrstaNastave.getItemCount() - 1);
					btnObrisi.setEnabled(true);
					btnSpremi.setEnabled(true);
				}
				
			}
		});
		btnNovi.setBounds(91, 134, 75, 25);
		btnNovi.setText("Novi");
		
		
		
		Button btnZatvori = new Button(shlDefiniranjeKolegija, SWT.NONE);
		btnZatvori.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDefiniranjeKolegija.close();
			}
		});
		btnZatvori.setBounds(284, 219, 75, 25);
		btnZatvori.setText("Zatvori");
		
		Label lblVrstaNastave = new Label(shlDefiniranjeKolegija, SWT.NONE);
		lblVrstaNastave.setText("Vrsta nastave:");
		lblVrstaNastave.setBounds(198, 75, 91, 15);

	}
	
	boolean provjeraKolegijaVrsteNastave() {
		
	
		for (int index = 0; index < listaKolegijVrstaNastave.size(); index ++) {
			
			
					
			if (listaKolegijVrstaNastave.get(index).getKolegij().getNaziv().equalsIgnoreCase(korisnickiUnos.getKolegij().getNaziv()) &&
				listaKolegijVrstaNastave.get(index).getVrstaNastave().getNaziv().equalsIgnoreCase(korisnickiUnos.getVrstaNastave().getNaziv())) {
				MessageBox messageBox = new MessageBox(shlDefiniranjeKolegija, SWT.ICON_WARNING | SWT.OK );
		        
		        messageBox.setText("Greška");
		        messageBox.setMessage("Za kolegij '" + korisnickiUnos.getKolegij().getNaziv() + "'" +
		        					  " veæ postoji vrsta nastave '" + korisnickiUnos.getVrstaNastave().getNaziv() + "'");
		        messageBox.open();
		       
		        return false;
			}	
		}
		return true;
	}
	
}
