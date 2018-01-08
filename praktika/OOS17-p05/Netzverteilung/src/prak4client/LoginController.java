package prak4client;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import prak4client.com.denis.benutzer.BenutzerVerwaltungAdmin;
import prak4gemklassen.*;

public class LoginController {

	private boolean neuAnmeldung = false;
	
	private boolean remote = false;
	
	@FXML
	Label lblErrorMessage;
	
	@FXML
	TextField txtUserId;
	
	@FXML
	PasswordField txtPasswort;
	
	@FXML
	CheckBox cboxNeuAnmeldung;
	
	@FXML
	CheckBox cboxRemote;

	@FXML
	TextField txtRemoteAdresse;
	
	@FXML
	Button btnAusfuehren;

	Client refMainApplication;
	
	public void setORB(Client ma){
		refMainApplication = ma;
		txtRemoteAdresse.setText("localhost");
	}

	public void showErrorMessage(String s) {
		lblErrorMessage.setVisible(true);
		lblErrorMessage.setText(s);		
	}
		
	@FXML
	public void onCboxNeuAnmeldungClick(Event e){
		neuAnmeldung = cboxNeuAnmeldung.isSelected();
		System.out.println(neuAnmeldung);
	}
	
	@FXML
	public void onCboxRemoteClick(Event e){
		remote = cboxRemote.isSelected();
		txtRemoteAdresse.setDisable(!remote);
		if(remote == true){
			refMainApplication.admin = new ClientOrb();
		} else {
			refMainApplication.admin = new BenutzerVerwaltungAdmin();
			
		}
		
		System.out.println(remote);
	}
	
	@FXML
	public void onBtnAusfuehrenClick(Event e){
		Benutzer ben = new Benutzer(txtUserId.getText(),txtPasswort.getText());
		if(neuAnmeldung == false) {
			refMainApplication.benutzerLogin(ben);
		} else {
			refMainApplication.neuAnmeldung();
		}
	}

	@FXML
	public void ontxtPasswortChange(Event e){
		lblErrorMessage.setVisible(false);
	}
	
	@FXML
	public void ontxtAddresseChange(Event e){
		refMainApplication.address = new StringBuilder( txtRemoteAdresse.getText()).toString();
	}
}