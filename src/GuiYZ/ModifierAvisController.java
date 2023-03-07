/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import entities.Avis;
import entities.Voyage;
import services.AvisService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class ModifierAvisController implements Initializable {

    @FXML
    private Button AvisButton;
    @FXML
    private TextField IC;
    @FXML
    private Label Avis;
    @FXML
    private TextField AV;
    @FXML
    private Label Commentaire;
    @FXML
    private TextArea COMM;
    @FXML
    private Label id;
    @FXML
    private Button modifierAvis;
    AvisService as =new AvisService();
    @FXML
    private Button IdVoyage;
    @FXML
    private Button Accueil;
            /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setData(List<Avis> la) {
        id.setText(Integer.toString(la.get(0).getId_avis()));
        IC.setText(Integer.toString(la.get(0).getId_client()));
        AV.setText(Integer.toString(la.get(0).getAvis()));
        COMM.setText(la.get(0).getCommentaire());
    }

    @FXML
    private void VoyageButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageViewAdmin.fxml"));
            Parent root = loader.load();
            VoyageViewAdminController controller = loader.getController();
            IdVoyage.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void ModifierAvis(ActionEvent event) {
       try {
            
            Avis a = new Avis();
            a.setId_avis(Integer.parseInt(id.getText()));
            a.setId_client(Integer.parseInt(IC.getText()));
            a.setAvis(Integer.parseInt(AV.getText()));
            a.setCommentaire(COMM.getText());
            as.Update(a);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvisViewAdmin.fxml"));
            Parent root = loader.load();
            AvisViewAdminController controller = loader.getController();
            AvisButton.getScene().setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModifierVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @FXML
    private void AvisButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvisViewAdmin.fxml"));
            Parent root = loader.load();
            AvisViewAdminController controller = loader.getController();
            AvisButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../interfaces/Acceuille_admin.fxml"));
            Parent root = loader.load();
            Accueil.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
