package DefiniranjeKriterijaOcjena;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DefKritOcjeneView extends Dialog {

	protected Object result;
	protected Shell shlDefiniranjeKriterijaOcjene;
	private Text txtNazivKriterija;
	
	private KriterijModel korisnickiUnos = new KriterijModel();
	private DefKritOcjeneCTRL defKritOcjeneCTRL = new DefKritOcjeneCTRL();
	private List<KriterijModel> listaKriterija;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DefKritOcjeneView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDefiniranjeKriterijaOcjene.open();
		shlDefiniranjeKriterijaOcjene.layout();
		Display display = getParent().getDisplay();
		while (!shlDefiniranjeKriterijaOcjene.isDisposed()) {
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
		shlDefiniranjeKriterijaOcjene = new Shell(getParent(), getStyle());
		shlDefiniranjeKriterijaOcjene.setSize(450, 300);
		shlDefiniranjeKriterijaOcjene.setText("Definiranje kriterija ocjene");
		
		Button btnSpremi = new Button(shlDefiniranjeKriterijaOcjene, SWT.NONE);
		Button btnObrisi = new Button(shlDefiniranjeKriterijaOcjene, SWT.NONE);
		btnObrisi.setEnabled(false);
		
		btnObrisi.setBounds(359, 134, 75, 25);
		btnObrisi.setText("Obri\u0161i");
		
		Combo cmbListaKriterija = new Combo(shlDefiniranjeKriterijaOcjene, SWT.READ_ONLY);
		cmbListaKriterija.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				korisnickiUnos = listaKriterija.get(cmbListaKriterija.getSelectionIndex());
				txtNazivKriterija.setText(korisnickiUnos.getNaziv());
				
				
				btnObrisi.setEnabled(true);
				btnSpremi.setEnabled(true);
				
			}
		});
		cmbListaKriterija.setBounds(10, 37, 424, 23);
		
		listaKriterija = defKritOcjeneCTRL.dohvatiKriterijeOcjena();
		
		for (int index = 0; index < listaKriterija.size(); index++) {
			cmbListaKriterija.add(listaKriterija.get(index).getNaziv(), index);	
		}
		
		btnObrisi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox messageBox = new MessageBox(shlDefiniranjeKriterijaOcjene, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
		        
		        messageBox.setText("Brisanje");
		        messageBox.setMessage("Želite li obrisati izabrani kriterij?");
		        int buttonID = messageBox.open();
		        switch(buttonID) {
		          case SWT.YES:
		        	  defKritOcjeneCTRL.obrisi(korisnickiUnos);
						
						cmbListaKriterija.setText("");
						cmbListaKriterija.remove(cmbListaKriterija.getSelectionIndex());
						btnSpremi.setEnabled(false);
						txtNazivKriterija.setText("");
		          case SWT.NO:
		            // exits here ...
		            break;
		          case SWT.CANCEL:
		            // does nothing ...
		        }
		        
				
				
				
			}
		});
		
		
		
		
		Label lblKriterijiOcjene = new Label(shlDefiniranjeKriterijaOcjene, SWT.NONE);
		lblKriterijiOcjene.setBounds(10, 16, 110, 15);
		lblKriterijiOcjene.setText("Kriteriji ocjene:");
		
		txtNazivKriterija = new Text(shlDefiniranjeKriterijaOcjene, SWT.BORDER);
		txtNazivKriterija.setBounds(10, 96, 424, 21);
		
		Label lblNewLabel = new Label(shlDefiniranjeKriterijaOcjene, SWT.NONE);
		lblNewLabel.setBounds(10, 75, 91, 15);
		lblNewLabel.setText("Naziv kriterija:");
		
		
		btnSpremi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				korisnickiUnos = new KriterijModel();
				
				korisnickiUnos.setNaziv(txtNazivKriterija.getText());
				
				
				if (provjeraKriterijaOcjene(korisnickiUnos)) {
					defKritOcjeneCTRL.spremi(korisnickiUnos);			
					cmbListaKriterija.setText(korisnickiUnos.getNaziv());
					cmbListaKriterija.setItem(cmbListaKriterija.getSelectionIndex(), 
											  korisnickiUnos.getNaziv());
				}
				
			}
		});
		btnSpremi.setBounds(10, 134, 75, 25);
		btnSpremi.setText("Spremi");
		
		Button btnNovi = new Button(shlDefiniranjeKriterijaOcjene, SWT.NONE);
		btnNovi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				korisnickiUnos = new KriterijModel();
		
				korisnickiUnos.setNaziv(txtNazivKriterija.getText());
				korisnickiUnos.setID(generirajNoviID());
				
				if (provjeraKriterijaOcjene(korisnickiUnos)) {
					defKritOcjeneCTRL.ubaci(korisnickiUnos);
					
					cmbListaKriterija.setText(korisnickiUnos.getNaziv());
					cmbListaKriterija.add(korisnickiUnos.getNaziv());
					cmbListaKriterija.select(cmbListaKriterija.getItemCount() - 1);
					btnObrisi.setEnabled(true);
					btnSpremi.setEnabled(true);	
				}
				
			}
		});
		btnNovi.setBounds(91, 134, 75, 25);
		btnNovi.setText("Novi");
		
		
		
		Button btnZatvori = new Button(shlDefiniranjeKriterijaOcjene, SWT.NONE);
		btnZatvori.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDefiniranjeKriterijaOcjene.close();
			}
		});
		btnZatvori.setBounds(359, 236, 75, 25);
		btnZatvori.setText("Zatvori");

	}
	
	boolean provjeraKriterijaOcjene(KriterijModel kriterij) {
		if (kriterij.getNaziv().isEmpty()) {
			MessageBox messageBox = new MessageBox(shlDefiniranjeKriterijaOcjene, SWT.ICON_WARNING | SWT.OK );
	        
	        messageBox.setText("Greška");
	        messageBox.setMessage("Naziv kriterija ne može biti prazan.");
	        messageBox.open();
	        
	        return false;
	
		}
		
		for (int index = 0; index < listaKriterija.size(); index ++) {
			
			if (listaKriterija.get(index).getNaziv().equalsIgnoreCase(kriterij.getNaziv())) {
				MessageBox messageBox = new MessageBox(shlDefiniranjeKriterijaOcjene, SWT.ICON_WARNING | SWT.OK );
		        
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
		for (int index = 0; index < listaKriterija.size(); index ++) {
			if (listaKriterija.get(index).getID() > zadnjiID) {
				zadnjiID = listaKriterija.get(index).getID();
			}	
		}
		return zadnjiID + 1;	
	}
}
