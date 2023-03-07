/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import entities.Avis;
import services.AvisService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class FormAjoutAvisAdminController implements Initializable {

    @FXML
    private Button AvisButton;
    @FXML
    private TextField IC;
    @FXML
    private TextField AV;
    @FXML
    private TextField COMM;
    @FXML
    private Button Ajouter_Avis;
    @FXML
    private Label Avis;
    @FXML
    private Label Commentaire;
    AvisService as =new AvisService();
    @FXML
    private Button IdVoyagee;
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
    private void AjouterAvis(ActionEvent event) {
        try {
            
            Avis a = new Avis();
            a.setId_client(Integer.parseInt(IC.getText()));
            a.setAvis(Integer.parseInt(AV.getText()));
            a.setCommentaire(COMM.getText());
            as.Create(a);
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
    private void VoyageButton(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageViewAdmin.fxml"));
            Parent root = loader.load();
            VoyageViewAdminController controller = loader.getController();
            IdVoyagee.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
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
