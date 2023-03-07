/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Type;
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
import javafx.scene.control.TextField;
import services.TypeService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormAjouterTypeAdminController implements Initializable {

    @FXML
    private Button Type;
    @FXML
    private TextField type;
    @FXML
    private TextField ges;
    @FXML
    private Button Ajouter_Type;
    
    TypeService ts = new TypeService();
    @FXML
    private Button Reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Ajouter_Type.setDisable(true);
        ges.textProperty().addListener((observable, oldValue, newValue) -> {
            Ajouter_Type.setDisable(newValue.isEmpty()||type.getText().isEmpty());
        });
        
    }    

    @FXML
    private void TypeButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeViewAdmin.fxml"));
            Parent root = loader.load();
            ReclamationViewAdminController controller = loader.getController();
            Type.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
      

    @FXML
    private void AjouterType(ActionEvent event) {
        try {
            
            Type t = new Type();
            t.setType(type.getText());
            t.setGestion(ges.getText());
            ts.ajouter(t);
            System.out.println("Type ajouter avec succes");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeViewAdmin.fxml"));
            Parent root = loader.load();
            TypeViewAdminController controller = loader.getController();
            Ajouter_Type.getScene().setRoot(root);
            ges.clear();
        } catch (SQLException ex) {
            System.out.println("gg1" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ReclamationViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ReclamationButton(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationViewAdmin.fxml"));
            Parent root = loader.load();
            ReclamationViewAdminController controller = loader.getController();
            Reclamation.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
