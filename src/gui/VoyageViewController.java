/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class VoyageViewController implements Initializable {

    @FXML
    private TextField Search;
    @FXML
    private Button SearchButton;
    @FXML
    private Button VoyageButton;
    @FXML
    private Button Ajouter_Voyage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SearchButton(ActionEvent event) {
    }

    @FXML
    private void VoyageButton(ActionEvent event) {
    }

    @FXML
    private void ShowVoyageForm(ActionEvent event) {
    }
    
}
