/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationItemController implements Initializable {

    @FXML
    private Label email;
    @FXML
    private Label emaillable;
    @FXML
    private Label Sujet;
    @FXML
    private Label Sujetlable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setData (Reclamation r){
        emaillable.setText(r.getEmail());
        Sujetlable.setText(r.getSujet());
    }
    
}
