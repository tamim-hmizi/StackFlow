/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Stock;
import services.StockService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class StockDetailsController implements Initializable {

    @FXML
    private TextField emplacement;
    @FXML
    private Button UpdateButton;
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
        id_produit.setText(Integer.toString(s.getId_produit()));
        id.setText(Integer.toString(s.getId()));
        emplacement.setText(s.getEmplacement());

    }

    @FXML
    private void UpdateShow(ActionEvent event) {
        try {
            Stock s = new Stock();
            s.setId_produit(Integer.parseInt(id_produit.getText()));
            s.setId(Integer.parseInt(id.getText()));
            s.setEmplacement(emplacement.getText());
            ss.Update(s);
            Stage stage = (Stage) UpdateButton.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            Logger.getLogger(StockDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
