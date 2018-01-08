package application;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import com.denis.benutzer.*;

public class AnmeldungsController {

	@FXML
	Label lblErrorMessage;
	
	@FXML
	TextField txtUsrID;
	
	@FXML
	PasswordField txtPasswort;
	
	@FXML
	PasswordField txtWiederholung;
	
	@FXML
	Button btnAusfuehren;
	
	MainApplication refMainApplication;
	
	public void showErrorMessage(String s) {
		lblErrorMessage.setText(s);
		lblErrorMessage.setVisible(true);
	}
	
	public void setORB(MainApplication ma){
		refMainApplication = ma;
	}
	
	@FXML
	public void onBtnAusfuehrenClick(Event e) {
		if(txtPasswort.getText().equals(txtWiederholung.getText())) {
			Benutzer ben = new Benutzer(txtUsrID.getText(),txtPasswort.getText());
			refMainApplication.neuerBenutzer(ben);
		} else {
			lblErrorMessage.setText("Passwort != Wiederholung");
			lblErrorMessage.setVisible(true);
		}
	}
	
	@FXML
	public void ontxtPasswortChange(Event e){
		lblErrorMessage.setVisible(false);
	}
	
}