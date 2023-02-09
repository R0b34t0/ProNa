package ProNa_App;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import DefiniranjeKolegija.DefKolegijaView;
import DefiniranjeKriterijaOcjena.DefKritOcjeneView;
import KolegijVrstaNastave.KolegijVrstaNastaveView;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ProNaApp {

	protected Shell shlProna;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProNaApp window = new ProNaApp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlProna.open();
		shlProna.layout();
		while (!shlProna.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlProna = new Shell();
		shlProna.setSize(450, 300);
		shlProna.setText("ProNa");
		
		Menu menu = new Menu(shlProna, SWT.BAR);
		shlProna.setMenuBar(menu);
		
		MenuItem mntmGlavniIzbornik = new MenuItem(menu, SWT.CASCADE);
		mntmGlavniIzbornik.setText("Glavni izbornik");
		
		Menu menu_1 = new Menu(mntmGlavniIzbornik);
		mntmGlavniIzbornik.setMenu(menu_1);
		
		MenuItem mntmDefiniranjeKriterijaOcjene = new MenuItem(menu_1, SWT.NONE);
		mntmDefiniranjeKriterijaOcjene.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				DefKritOcjeneView defKritOcjeneView = new DefKritOcjeneView(shlProna, SWT.DIALOG_TRIM);
				
				defKritOcjeneView.open();
			}
		});
		mntmDefiniranjeKriterijaOcjene.setText("Definiranje kriterija ocjena");
		
		MenuItem mntmDefiniranjeKolegija = new MenuItem(menu_1, SWT.NONE);
		mntmDefiniranjeKolegija.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				DefKolegijaView defKolegijaView = new DefKolegijaView(shlProna, SWT.DIALOG_TRIM);
				
				defKolegijaView.open();
				
			}
		});
		mntmDefiniranjeKolegija.setText("Definiranje kolegija");
		
		MenuItem mntmDefiniranjeVrsteSata = new MenuItem(menu_1, SWT.NONE);
		mntmDefiniranjeVrsteSata.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				KolegijVrstaNastaveView kolegijVrstaNastaveView = new KolegijVrstaNastaveView(shlProna, SWT.DIALOG_TRIM);
				kolegijVrstaNastaveView.open();
			}
			
			

		});
		mntmDefiniranjeVrsteSata.setText("Definiranje vrste sata po kolegiju");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmZatvori = new MenuItem(menu_1, SWT.NONE);
		mntmZatvori.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shlProna.close();
			}
		});
		mntmZatvori.setText("Zatvori");

	}
}
