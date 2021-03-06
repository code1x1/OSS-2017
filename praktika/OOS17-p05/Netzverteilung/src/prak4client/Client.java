package prak4client;

import java.io.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import prak4gemklassen.*;
import prak4client.com.denis.benutzer.*;


public class Client extends Application {
	
	private Stage refStage;
	
	private LoginController lc;
	
	private AnmeldungsController ac;
	
	private AnwendungsController awc;
	
	public BenutzerVerwaltung admin;
	
	String address;
	
	public Client() {
		admin = new BenutzerVerwaltungAdmin();
	}
	
	@Override
	public void start(Stage primaryStage) {
		refStage = primaryStage;
		
		
		//1. Erzeugung eines Objekts der Klasse BenutzerVerwaltungAdmin.
		admin = new BenutzerVerwaltungAdmin();
		
		//2. Anfrage an den Benutzer, ob die Datenhaltung initialisiert werden soll
		System.out.println("dbInitialisieren(1/0):");
		BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
		try {
			int dbInitialisieren = Integer.parseInt(din.readLine());
			if(dbInitialisieren == 1) {
				((BenutzerVerwaltungAdmin) admin).dbInitialisieren();
				System.out.println("Initialisiere Benutzer.DB Datei...");
			} else if(dbInitialisieren == 0) {
				System.out.println("Initialisierung �bersprungen");
			}
		} catch (NumberFormatException | IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
				
		//3. Erzeugung einer LoginScene mit �bergabe der eigenen Referenz an deren Controller			
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			Parent root = loader.load();
			lc = loader.getController();
			lc.setORB(this);
			refStage.setTitle("Benutzerverwaltung - Login");
			refStage.setScene(new Scene(root));
			refStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Nach Anmeldung zum Loginfenster zur�ck wechseln
	 * Bei nicht erfolgreichem benutzerEintragen wird 
	 * die Exception in Anmeldungscontroller angezeigt
	 * @param benutzer
	 */
	void neuerBenutzer(Benutzer benutzer){
		
		try {
			admin.benutzerEintragen(benutzer);
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				Parent root = loader.load();
				lc = loader.getController();
				lc.setORB(this);
				refStage.setTitle("Benutzerverwaltung - Login");
				refStage.setScene(new Scene(root));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			ac.showErrorMessage("Ein Fehler ist aufgetreten!");
		} catch (BenutzerVorhandenException e) {
			e.printStackTrace();
			ac.showErrorMessage(e.getMessage());
		} 
	}
	
	/**
	 * �ffnet das Anmeldungsfenster
	 */
	void neuAnmeldung() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Anmeldung.fxml"));
			Parent root = loader.load();
			ac = loader.getController();
			ac.setORB(this);
			refStage.setTitle("Benutzerverwaltung - Anmeldung");
			refStage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Benutzerlogin lokal order remote
	 * Wenn erfolgreich �ffnen der Anwendungsansicht
	 * @param ben
	 */
	public void benutzerLogin(Benutzer ben) {
		if(address != null && !address.isEmpty()) {
			((ClientOrb) admin).setAddress(address);
		}
		try {
			if(admin.benutzerOk(ben)) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Anwendung.fxml"));
					Parent root = loader.load();
					awc = loader.getController();
					awc.setORB(this);
					refStage.setTitle("Benutzerverwaltung - Anwendung");
					refStage.setScene(new Scene(root));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				lc.showErrorMessage("Benutzer ist nicht vorhanden!");
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ben);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
