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
import services.TypeService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TypeItemAdminController implements Initializable {

    @FXML
    private Label email;
    @FXML
    private Label typelable;
    @FXML
    private Label Sujet;
    @FXML
    private Label geslable;
    @FXML
    private Button mod;
    @FXML
    private Label id;
    @FXML
    private Button sup;
    
    TypeService ts = new TypeService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    public void setData (Type t){
        id.setText(Integer.toString(t.getId_type()));
        geslable.setText(t.getGestion());
        typelable.setText(t.getType());
        
    }

    @FXML
    private void modifier(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormModifierTypeAdmin.fxml"));
            Parent root = loader.load();
            FormModifierTypeAdminController controller = loader.getController();
            Type t = new Type();
            t.setId_type(Integer.parseInt(id.getText()));
            List<Type> lt= ts.recupererSingel(t);
            controller.setData(lt);
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
            Type t = new Type();
            t.setId_type(Integer.parseInt(id.getText()));
            ts.supprimer(t);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
