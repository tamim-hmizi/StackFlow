/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Stock;
import services.StockService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class FormAjouterStockController implements Initializable {

    @FXML
    private TextField emplacement;
    @FXML
    private Label id;
    @FXML
    private Button AddButton;
    StockService ss = new StockService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addShow(ActionEvent event) {
        try {
            Stock s = new Stock();
            s.setId_produit(41);
            s.setEmplacement(emplacement.getText());
            ss.Create(s);
            Stage stage = (Stage) AddButton.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StockViewAdmin.fxml"));
            Parent root = loader.load();
            StockViewAdminController controller = loader.getController();
            stage.close();
            id.getScene().setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(FormAjouterStockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormAjouterStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
