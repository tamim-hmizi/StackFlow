/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormAjouterReclamationController implements Initializable {

    @FXML
    private Button Reclamation;
    @FXML
    private TextField email;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea msg;
    @FXML
    private Button Ajouter_Reclamation;
    
    ReclamationService ps = new ReclamationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ReclamationButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationViewClient.fxml"));
            Parent root = loader.load();
            ReclamationViewClientController controller = loader.getController();
            Reclamation.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void AjouterReclamation(ActionEvent event)  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationViewClient.fxml"));
            Parent root = loader.load();
            ReclamationViewClientController controller = loader.getController();
            Ajouter_Reclamation.getScene().setRoot(root);
            Reclamation r = new Reclamation();
            r.setEmail(email.getText());
            r.setSujet(sujet.getText());
            r.setMessage(msg.getText());
            ps.ajouter(r);
            
            
            System.out.println("Reclamation ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("gg1" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ReclamationViewClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
