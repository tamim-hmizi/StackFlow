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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationItemAdminController implements Initializable {

    @FXML
    private Label email;
    @FXML
    private Label emaillable;
    @FXML
    private Label Sujet;
    @FXML
    private Label Sujetlable;
    @FXML
    private Button mod;
    @FXML
    private Button sup;
    @FXML
    private Label id;
    ReclamationService rs = new ReclamationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     public void setData (Reclamation r){
        id.setText(Integer.toString(r.getId_reclamation()));
        emaillable.setText(r.getEmail());
        Sujetlable.setText(r.getSujet());
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormModifierReclamationAdmin.fxml"));
            Parent root = loader.load();
            FormModifierReclamationAdminController controller = loader.getController();
            Reclamation r = new Reclamation();
            r.setId_reclamation(Integer.parseInt(id.getText()));
            List<Reclamation> lr= rs.recupererSingel(r);
            controller.setData(lr);
            mod.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        try {
            Reclamation r = new Reclamation();
            r.setId_reclamation(Integer.parseInt(id.getText()));
            rs.supprimer(r);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationViewAdmin.fxml"));
            Parent root = loader.load();
            sup.getScene().setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReclamationItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
