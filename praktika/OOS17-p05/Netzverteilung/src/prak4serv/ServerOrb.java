package prak4serv;

import java.io.*;
import java.net.*;

import prak4gemklassen.Benutzer;
import prak4gemklassen.BenutzerVorhandenException;
import prak4serv.com.denis.benutzer.BenutzerVerwaltungAdmin;

public class ServerOrb {

	public ServerOrb(BenutzerVerwaltungAdmin bva) throws IOException {
		
		
		try {
			ServerSocket server = new ServerSocket(4711);

			System.out.println(">Server gestartet auf port 4711...");
	
			initdb(bva);
			
			// Warten auf Verbindungsaufnahme durch Client:
			while (true) {
			
				Socket client = server.accept();
				// Verbindung annehmen und korresp. Streams erzeugen:
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			
				// Lesen der Methodenkodierung:
				String methode = (String) in.readObject();
				// Lesen des Parameters:
				Benutzer ben = (Benutzer) in.readObject();
				
				// Methodenimplementierungen durch Delegation an
				// Dienstanbieterobjekt:
				if (methode.equals("benutzerOk")) {
					System.out.println("In benutzerOk");
					out.writeBoolean(bva.benutzerOk(ben));
					// Sicherstellen, dass Ergebnis transportiert wird:
					out.flush();
				}
				if (methode.equals("benutzerEintragen")) {
					System.out.println("In benutzerEintragen");
					try {
						bva.benutzerEintragen(ben);
						out.writeObject("Benutzer remote eintragen erfolgreich.");
					} catch (BenutzerVorhandenException e) {
						out.writeObject("BenutzerVorhandenException");
					}
					// Sicherstellen, dass Ergebnis transportiert wird:
					out.flush();
				}
				client.close();
			
			}
			
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}

	private void initdb(BenutzerVerwaltungAdmin bva) {

		// Anfrage an den Benutzer, ob die Datenhaltung initialisiert werden soll
		System.out.println("dbInitialisieren(1/0):");
		BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
		try {
			int dbInitialisieren = Integer.parseInt(din.readLine());
			if(dbInitialisieren == 1) {
				bva.dbInitialisieren();
				System.out.println("Initialisiere server/Benutzer.DB Datei...");
			} else if(dbInitialisieren == 0) {
				System.out.println("Initialisierung �bersprungen");
			}
		} catch (NumberFormatException | IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}

}