/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Type;
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
import javafx.scene.control.TextField;
import services.TypeService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormModifierTypeAdminController implements Initializable {

    @FXML
    private Button Type;
    @FXML
    private TextField type;
    @FXML
    private TextField gestion;
    @FXML
    private Label id;
    @FXML
    private Button Modifier_Type;
    
    TypeService ps = new TypeService();
    @FXML
    private Button Reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Modifier_Type.setDisable(true);
        gestion.textProperty().addListener((observable, oldValue, newValue) -> {
            Modifier_Type.setDisable(newValue.isEmpty()||type.getText().isEmpty());
        });
    }    
     void setData(List<Type> lt) {
        id.setText(Integer.toString(lt.get(0).getId_type()));
        type.setText(lt.get(0).getType());
        gestion.setText(lt.get(0).getGestion());
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

    @FXML
    private void ModifierType(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeViewAdmin.fxml"));
            Parent root = loader.load();
            TypeViewAdminController controller = loader.getController();
            Modifier_Type.getScene().setRoot(root);
            Type t = new Type();
            t.setId_type(Integer.parseInt(id.getText()));
            t.setType(type.getText());
            t.setGestion(gestion.getText());
            ps.modifier(t);

        } catch (SQLException e) {
            Logger.getLogger(FormModifierReclamationAdminController.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(FormModifierReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
