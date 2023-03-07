/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import entities.Voyage;
import services.VoyageService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class VoyageItemAdminController implements Initializable {

    @FXML
    private Label idDep;
    @FXML
    private Label idArri;
    @FXML
    private Label idDate;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Label id;
    
    VoyageService vs = new VoyageService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UpdateButton(ActionEvent event) {
        try {
            
            Voyage v = new Voyage();
            v.setId_Voyage(Integer.parseInt(id.getText()));
            List<Voyage> lv = vs.ReadSingel(v);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierVoyage.fxml"));
            Parent root = loader.load();
            ModifierVoyageController controller = loader.getController();
            controller.setData(lv);
            UpdateButton.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(VoyageItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VoyageItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteButton(ActionEvent event) {
        try {
            try {
                Voyage v = new Voyage();
                v.setId_Voyage(Integer.parseInt(id.getText()));
                vs.Delete(v);
                
            } catch (SQLException ex) {
                Logger.getLogger(VoyageItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageViewAdmin.fxml"));
            Parent root = loader.load();
            VoyageViewAdminController controller = loader.getController();
            DeleteButton.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(VoyageItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void setData(Voyage v){
         id.setText(Integer.toString(v.getId_Voyage()));
        DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        String date_string = date_format.format(v.getDate_depart());
        idDate.setText(date_string);
        idArri.setText(v.getStation_arrive());
        idDep.setText(v.getStation_depart());
        
    }
}
