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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormModifierReclamationAdminController implements Initializable {

    @FXML
    private Button Reclamation;
    @FXML
    private TextField email;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea msg;
    @FXML
    private Button Modifier_Reclamation;

    ReclamationService rs = new ReclamationService();
    @FXML
    private Label id;
    @FXML
    private Button Type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Modifier_Reclamation.setDisable(true);
        msg.textProperty().addListener((observable, oldValue, newValue) -> {
            Modifier_Reclamation.setDisable(newValue.isEmpty()||sujet.getText().isEmpty()||email.getText().isEmpty());
        });
    }

    void setData(List<Reclamation> lr) {
        id.setText(Integer.toString(lr.get(0).getId_reclamation()));
        email.setText(lr.get(0).getEmail());
        sujet.setText(lr.get(0).getSujet());
        msg.setText(lr.get(0).getMessage());
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

    @FXML
    private void ModifierReclamation(ActionEvent event) {
        try {
            
            Reclamation r = new Reclamation();
            r.setId_reclamation(Integer.parseInt(id.getText()));
            r.setEmail(email.getText());
            r.setSujet(sujet.getText());
            r.setMessage(msg.getText());
            rs.modifier(r);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationViewAdmin.fxml"));
            Parent root = loader.load();
            ReclamationViewAdminController controller = loader.getController();
            Modifier_Reclamation.getScene().setRoot(root);
            msg.clear();

        } catch (SQLException e) {
            Logger.getLogger(FormModifierReclamationAdminController.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(FormModifierReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void TypeButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeViewAdmin.fxml"));
            Parent root = loader.load();
            TypeViewAdminController controller = loader.getController();
            Type.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

}
