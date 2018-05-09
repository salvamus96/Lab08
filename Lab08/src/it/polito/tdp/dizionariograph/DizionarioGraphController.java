package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="inputNumeroLettere"
    private TextField inputNumeroLettere; // Value injected by FXMLLoader

    @FXML // fx:id="inputParola"
    private TextField inputParola; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeneraGrafo"
    private Button btnGeneraGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaGradoMax"
    private Button btnTrovaGradoMax; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaVicini"
    private Button btnTrovaVicini; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	
    	try {
    	
    		int numLettere = Integer.parseInt(this.inputNumeroLettere.getText());
    		Graph <String, DefaultEdge> graph = model.createGraph(numLettere);
    		
    		System.out.println(graph.vertexSet().size());
    		System.out.println(graph.edgeSet().size());
    		
//    		this.txtResult.appendText("VERTICI DEL GRAFO CREATO: \n");
//    		for (String vertice : graph.vertexSet())
//    			this.txtResult.appendText(vertice + "\n");
//
//    		this.txtResult.appendText("\n\nARCHI DEL GRAFO CREATO: \n");
//    		for (DefaultEdge arco : graph.edgeSet())
//    			this.txtResult.appendText(arco + "\n");

    		this.txtResult.appendText(graph.toString());
    		
    	}catch (NumberFormatException e) {
    		this.txtResult.appendText("Inserire un numero per determinare il numero "+
    									"delle lettere delle parole da cercare!");
    	}
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	this.txtResult.clear();
    	
    	String parola = this.inputParola.getText();
    	if (parola.isEmpty()) {
    		this.txtResult.appendText("Inserire una parola!");
    		return;
    	}
    	
    	try {	
    	
    		int numLettere = Integer.parseInt(this.inputNumeroLettere.getText());
    		model.createGraph(numLettere);

    		this.txtResult.appendText("Le parole connese a " + parola + ":\n");
    		for (String arco : model.displayNeighbours(parola))
    			this.txtResult.appendText(arco + "\n");
    	
    	}catch (NumberFormatException e) {
    		this.txtResult.appendText("Inserire un numero per determinare il numero "+
    									"delle lettere delle parole da cercare!");
    	}
    	
    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	this.txtResult.clear();
    	
    	try {	
    		int numLettere = Integer.parseInt(this.inputNumeroLettere.getText());
    		model.createGraph(numLettere);
    		
    		this.txtResult.appendText(model.findMaxDegree());
        	
    	}catch (NumberFormatException e) {
    		this.txtResult.appendText("Inserire un numero per determinare il numero "+
    									"delle lettere delle parole da cercare!");
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	this.txtResult.clear();
    	this.inputNumeroLettere.clear();
    	this.inputParola.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
    
    
}

