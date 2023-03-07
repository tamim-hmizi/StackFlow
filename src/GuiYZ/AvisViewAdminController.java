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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class AvisViewAdminController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private AvisService as = new AvisService();
    @FXML
    private Button Ajouter_Avis;
    @FXML
    private Button VoyageId;
    @FXML
    private Button AvisButton;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        try {
            List<Avis> a = as.Read();
            for (int i = 0; i < a.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("AvisItemAdmin.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                AvisItemAdminController pc = fxmlloader.getController();
                pc.setData(a.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                System.out.println();

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(VoyageItemsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AvisViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void AjouterAvis(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjoutAvisAdmin.fxml"));
            Parent root = loader.load();
            FormAjoutAvisAdminController controller = loader.getController();
            Ajouter_Avis.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }

    @FXML
    private void ShowVoyage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageViewAdmin.fxml"));
            Parent root = loader.load();
            VoyageViewAdminController controller = loader.getController();
            VoyageId.getScene().setRoot(root);

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
