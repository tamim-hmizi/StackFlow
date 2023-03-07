/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import entities.Stock;
import services.StockService;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class StockViewAdminController implements Initializable {

    @FXML
    private Button ProduitButton;
    @FXML
    private Button Ajouter_Produits;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label emplacement;
    @FXML
    private VBox StockLayouts;
    StockService ss = new StockService();
    @FXML
    private Button stockButton;
    @FXML
    private TextField Search;
    @FXML
    private Button searchButton;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            List<Stock> p = ss.Read();
            for (int i = 0; i < p.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("StockAdminItems.fxml"));
                HBox hbox =fxmlloader.load();
                StockAdminItemsController pc = fxmlloader.getController();
                pc.setData(p.get(i));
                StockLayouts.getChildren().add(hbox);
                
            }   } catch (SQLException ex) {    
            Logger.getLogger(ProduitViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProduitViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    


    @FXML
    private void ShowForm(ActionEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterStock.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root,600,400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajouter Emplacement");
            primaryStage.show();
            

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StockView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StockViewAdmin.fxml"));
            Parent root = loader.load();
            StockViewAdminController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ProduitView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewAdmin.fxml"));
            Parent root = loader.load();
            ProduitViewAdminController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Seach.fxml"));
            Parent root = loader.load();
            SeachController controller = loader.getController();
            Produits p = new Produits();
            p.setTitre(Search.getText());
            controller.setDate(p);
            searchButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
