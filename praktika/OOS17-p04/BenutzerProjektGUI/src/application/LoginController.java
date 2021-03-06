package application;

import com.denis.benutzer.Benutzer;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class LoginController {

	private boolean neuAnmeldung = false;
	
	@FXML
	TextField txtUserId;
	
	@FXML
	PasswordField txtPasswort;
	
	@FXML
	CheckBox cboxNeuAnmeldung;
	
	@FXML
	Button btnAusfuehren;
	
	@FXML
	public void onCboxNeuAnmeldungClick(Event e){
		
		neuAnmeldung = cboxNeuAnmeldung.isSelected();
		System.out.println(neuAnmeldung);
	}
	
	@FXML
	public void onBtnAusfuehrenClick(Event e){
		
		Benutzer ben = new Benutzer(txtUserId.getText(),txtPasswort.getText());
		System.out.println(ben);
		Stage window = (Stage) txtUserId.getScene().getWindow();
		window.close();
	}
	
}
