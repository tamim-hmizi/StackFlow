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
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class ModifierVoyageController implements Initializable {

    @FXML
    private Button VoyageButton;
    @FXML
    private TextField SD;
    @FXML
    private TextField SA;
    @FXML
    private DatePicker DD;
    @FXML
    private DatePicker DA;
    @FXML
    private Button Modifier_Voyage;
    VoyageService vs = new VoyageService();
    @FXML
    private Label id;
    @FXML
    private Button idavis;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void VoyageButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageViewAdmin.fxml"));
            Parent root = loader.load();
            VoyageViewAdminController controller = loader.getController();
            VoyageButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void ModifierVoyage(ActionEvent event) {
        try {
            
            Voyage v = new Voyage();
            v.setId_Voyage(Integer.parseInt(id.getText()));
            v.setStation_depart(SD.getText());
            v.setStation_arrive(SA.getText());
            Date da = Date.valueOf(DA.getValue());
            v.setDate_arrivee(da);
            Date dd = Date.valueOf(DD.getValue());
            v.setDate_depart(dd);
            vs.Update(v);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageViewAdmin.fxml"));
            Parent root = loader.load();
            VoyageViewAdminController controller = loader.getController();
            VoyageButton.getScene().setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModifierVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setData(List<Voyage> lv) {
        id.setText(Integer.toString(lv.get(0).getId_Voyage()));
        SD.setText(lv.get(0).getStation_depart());
        SA.setText(lv.get(0).getStation_arrive());
        DD.setValue(lv.get(0).getDate_depart().toLocalDate());
        DA.setValue(lv.get(0).getDate_arrivee().toLocalDate());
    }

    @FXML
    private void avisbutton(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvisViewAdmin.fxml"));
            Parent root = loader.load();
            AvisViewAdminController controller = loader.getController();
            idavis.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../interfaces/Acceuil_user.fxml"));
            Parent root = loader.load();
            Accueil.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
