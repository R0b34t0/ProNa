package DefiniranjeKolegija;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import DefiniranjeKriterijaOcjena.KriterijModel;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DefKolegijaView extends Dialog {

	protected Object result;
	protected Shell shlDefiniranjeKolegija;
	private Text txtNazivKolegija;
	
	private KolegijModel korisnickiUnos = new KolegijModel();
	private DefKolegijaCTRL defKolegijaCTRL = new DefKolegijaCTRL();
	private List<KolegijModel> listaKolegija;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DefKolegijaView(Shell parent, int style) {
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
		shlDefiniranjeKolegija.setSize(450, 300);
		shlDefiniranjeKolegija.setText("Definiranje kolegija");
		
		Button btnSpremi = new Button(shlDefiniranjeKolegija, SWT.NONE);
		Button btnObrisi = new Button(shlDefiniranjeKolegija, SWT.NONE);
		btnObrisi.setEnabled(false);
		
		btnObrisi.setBounds(359, 134, 75, 25);
		btnObrisi.setText("Obri\u0161i");
		
		Combo cmbListaKolegija = new Combo(shlDefiniranjeKolegija, SWT.READ_ONLY);
		cmbListaKolegija.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				korisnickiUnos = listaKolegija.get(cmbListaKolegija.getSelectionIndex());
				txtNazivKolegija.setText(korisnickiUnos.getNaziv());
				
				
				btnObrisi.setEnabled(true);
				btnSpremi.setEnabled(true);
				
			}
		});
		cmbListaKolegija.setBounds(10, 37, 424, 23);
		
		listaKolegija = defKolegijaCTRL.dohvatiKolegije();
		
		for (int index = 0; index < listaKolegija.size(); index++) {
			cmbListaKolegija.add(listaKolegija.get(index).getNaziv(), index);	
		}
		
		btnObrisi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox messageBox = new MessageBox(shlDefiniranjeKolegija, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
		        
		        messageBox.setText("Brisanje");
		        messageBox.setMessage("Želite li obrisati izabrani kolegij?");
		        int buttonID = messageBox.open();
		        switch(buttonID) {
		          case SWT.YES:
		        	  defKolegijaCTRL.obrisi(korisnickiUnos);
						
						cmbListaKolegija.setText("");
						cmbListaKolegija.remove(cmbListaKolegija.getSelectionIndex());
						btnSpremi.setEnabled(false);
						txtNazivKolegija.setText("");
		          case SWT.NO:
		            // exits here ...
		            break;
		          case SWT.CANCEL:
		            // does nothing ...
		        }
		        
				
				
				
			}
		});
		
		
		
		
		Label lblKolegiji = new Label(shlDefiniranjeKolegija, SWT.NONE);
		lblKolegiji.setBounds(10, 16, 110, 15);
		lblKolegiji.setText("Kolegiji:");
		
		txtNazivKolegija = new Text(shlDefiniranjeKolegija, SWT.BORDER);
		txtNazivKolegija.setBounds(10, 96, 424, 21);
		
		Label lblNazivKolegija = new Label(shlDefiniranjeKolegija, SWT.NONE);
		lblNazivKolegija.setBounds(10, 75, 91, 15);
		lblNazivKolegija.setText("Naziv kolegija:");
		
		
		btnSpremi.setEnabled(false);
		btnSpremi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				korisnickiUnos = new KolegijModel();
				korisnickiUnos.setNaziv(txtNazivKolegija.getText());
				
				
				if (provjeraKolegija(korisnickiUnos)){
					
					defKolegijaCTRL.spremi(korisnickiUnos);
					
					cmbListaKolegija.setText(korisnickiUnos.getNaziv());
					cmbListaKolegija.setItem(cmbListaKolegija.getSelectionIndex(), korisnickiUnos.getNaziv());
				}
				
				
			}
		});
		btnSpremi.setBounds(10, 134, 75, 25);
		btnSpremi.setText("Spremi");
		
		Button btnNovi = new Button(shlDefiniranjeKolegija, SWT.NONE);
		btnNovi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				korisnickiUnos = new KolegijModel();
		
				korisnickiUnos.setNaziv(txtNazivKolegija.getText());
			
				
				if (provjeraKolegija(korisnickiUnos)){
					defKolegijaCTRL.ubaci(korisnickiUnos);
					
					cmbListaKolegija.setText(korisnickiUnos.getNaziv());
					cmbListaKolegija.add(korisnickiUnos.getNaziv());
					cmbListaKolegija.select(cmbListaKolegija.getItemCount() - 1);
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
		btnZatvori.setBounds(359, 236, 75, 25);
		btnZatvori.setText("Zatvori");

	}
	
	boolean provjeraKolegija(KolegijModel kolegij) {
		if (kolegij.getNaziv().isEmpty()) {
			MessageBox messageBox = new MessageBox(shlDefiniranjeKolegija, SWT.ICON_WARNING | SWT.OK );
	        
	        messageBox.setText("Greška");
	        messageBox.setMessage("Naziv kolegija ne može biti prazan.");
	        messageBox.open();
	        
	        return false;
	
		}
		
		for (int index = 0; index < listaKolegija.size(); index ++) {
			if (listaKolegija.get(index).getNaziv().equalsIgnoreCase(korisnickiUnos.getNaziv())) {
				MessageBox messageBox = new MessageBox(shlDefiniranjeKolegija, SWT.ICON_WARNING | SWT.OK );
		        
		        messageBox.setText("Greška");
		        messageBox.setMessage("Naziv kriterija veæ postoji.");
		        messageBox.open();
		       
		        return false;
			}	
		}
		return true;
	}
	
	int generirajNoviID() {
		int zadnjiID = 0;
		for (int index = 0; index < listaKolegija.size(); index ++) {
			/*
			if (listaKolegija.get(index).getID() > zadnjiID) {
				zadnjiID = listaKolegija.get(index).getID();
			}	
			*/
		}
		return zadnjiID + 1;	
	}
}
