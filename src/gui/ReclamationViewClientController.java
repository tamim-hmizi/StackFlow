/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import javafx.geometry.Insets;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationViewClientController implements Initializable {

    @FXML
    private Button Reclamation;
    @FXML
    private Button Ajouter_Reclamation;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    
    ReclamationService ps = new ReclamationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        try {
            List<Reclamation> r = ps.recuperer();
            for (int i = 0; i < r.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ReclamationItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ReclamationItemController pc = fxmlloader.getController();
                pc.setData(r.get(i));
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
            Logger.getLogger(ReclamationViewClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationViewClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       

    @FXML
    private void ReclamationButton(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationViewClient.fxml"));
            Parent root = loader.load();
            ReclamationViewClientController controller = loader.getController();
            Reclamation.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterReclamation.fxml"));
            Parent root = loader.load();
            FormAjouterReclamationController controller = loader.getController();
            Ajouter_Reclamation.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
