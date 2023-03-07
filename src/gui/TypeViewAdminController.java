/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.TypeService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TypeViewAdminController implements Initializable {

    @FXML
    private Button Type;
    @FXML
    private Button Ajouter_Type;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
     TypeService ps = new TypeService();
    @FXML
    private Button Reclamation;
    @FXML
    private Button acceuil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         int column = 0;
        int row = 1;
        try {
            List<Type> t = ps.recuperer();
            for (int i = 0; i < t.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("TypeItemAdmin.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                TypeItemAdminController pc = fxmlloader.getController();
                pc.setData(t.get(i));
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
            Logger.getLogger(ReclamationViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void AjouterType(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterTypeAdmin.fxml"));
            Parent root = loader.load();
            FormAjouterTypeAdminController controller = loader.getController();
            Ajouter_Type.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
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

    @FXML
    private void acceuilButton(ActionEvent event) throws IOException {
         javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("../interfaces/Acceuille_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
