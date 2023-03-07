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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class StockAdminItemsController implements Initializable {

    @FXML
    private Label emplacement;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Label id;
    
    StockService ss = new StockService();
    @FXML
    private Label id_produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setData(Stock s) {
        id.setText(Integer.toString(s.getId()));
        id_produit.setText(Integer.toString(s.getId_produit()));
        emplacement.setText(s.getEmplacement());

    }

    @FXML
    private void UpdateForm(ActionEvent event) {
        try {
            Stock s = new Stock();
            s.setId(Integer.parseInt(id.getText()));
            s.setId_produit(Integer.parseInt(id_produit.getText()));
            s.setEmplacement(emplacement.getText());
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StockDetails.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root,600,400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Modifier Emplacement");
            primaryStage.show();
            System.out.println(s);
            StockDetailsController cr = loader.getController();
            cr.setData(s);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void DeleteForm(ActionEvent event) {
        try {
            Stock p = new Stock();
            p.setId(Integer.parseInt(id.getText()));
            ss.Delete(p);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StockViewAdmin.fxml"));
            Parent root = loader.load();
            StockViewAdminController controller = loader.getController();
            emplacement.getScene().setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsAdminItemsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StockAdminItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
