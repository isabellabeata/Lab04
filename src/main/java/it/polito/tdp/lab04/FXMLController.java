/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnIsIscritto"
    private Button btnIsIscritto; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscritti"
    private Button btnIscritti; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="btnOK"
    private Button btnOK; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCorso"
    private ComboBox<Corso> cmbCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCerca(ActionEvent event) {
  
    	String s= this.cmbCorso.getValue().getCodins();
    	Corso c= model.getCorso(s);
    	List<Studente> lista= model.getStudentiByCorso(c);
    	for(Studente si: lista) {
    		this.txtResult.appendText(si.toString()+"\n");
    	}
    	

    }

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	String s= this.txtMatricola.getText();
    	Integer i= Integer.parseInt(s);
    	List<Corso> lista= model.getCorsiStudente(i);
    	for(Corso ci: lista) {
    		this.txtResult.appendText(ci+"\n");
    	}
    	
    
    	

    }
    @FXML
    void handleIscritto(ActionEvent event) {
    	String s= this.txtMatricola.getText();
    	Integer i= Integer.parseInt(s);
    	Corso c= this.cmbCorso.getValue();
    	if(model.isIscrittoAlCorso(i, c)) {
    		
    		this.txtResult.setText("Lo studente è iscritto al corso");
    	}else this.txtResult.setText("Lo studente NON è iscritto al corso");
    	
    	
    }

    @FXML
    void handleIscrivi(ActionEvent event) {
    	String s= this.txtMatricola.getText();
    	Integer i= Integer.parseInt(s);
    	Studente stud= model.getStudenteByMatricola(i);
    	Corso cor=this.cmbCorso.getValue();
    	if(model.iscrivi(stud, cor)) {
    		this.txtResult.setText("Studente iscritto correttamente!");
    	}else this.txtResult.setText("Fare controlli");

    }

    @FXML
    void handleOK(ActionEvent event) {
    	this.txtMatricola.clear();
    	 String s = this.txtMatricola.getText();
         Integer i= Integer.parseInt(s);
        	if(s.length()==0) {
        		this.txtResult.setText("Inserire un valore valido");
        	}
        	else {
        		Studente stud= model.getStudenteByMatricola(i);
        		
        		this.txtNome.setText(stud.getNome());
        		this.txtCognome.setText(stud.getCognome());
        	}

    }

    @FXML
    void handleReset(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscritti != null : "fx:id=\"btnIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnOK != null : "fx:id=\"btnOK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIsIscritto != null : "fx:id=\"btnIsIscritto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorso != null : "fx:id=\"cmbCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
 
        

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	this.cmbCorso.getItems().add(null);
       for(Corso ci: model.getTuttiICorsi()) {
    	   this.cmbCorso.getItems().add(ci);
       }
    }

}
